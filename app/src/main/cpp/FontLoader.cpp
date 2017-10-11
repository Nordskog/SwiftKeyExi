//
// Created by Roughy on 1/28/2017.
//

//#include <string.h>

#include <string>
#include <vector>

#include <jni.h>

#include <codecvt>
#include <locale>

#include "log.h"

#include <iomanip>


#include <ft2build.h>   //AS says unsued but won't compile without it
#include FT_FREETYPE_H

#include "StringUtils.h"

const std::string LOGTAG = "Exi/FontLoader";

FT_Library library;
std::vector<FT_Face> faces;
bool libraryInit = false;

enum GlyphType
{
    INVALID = -1,
    NONE = 0,
    NORMAL = 1,
    BITMAP = 2
};

const uint32_t  VARIANT_SELECTOR_START = 0xFE00;
const uint32_t VARIANT_SELECTOR_END = 0xFE0F;

const uint32_t MONGOLIAN_VARIANT_SELECTOR_START = 0x180B;
const uint32_t MONGOLIAN_VARIANT_SELECTOR_END = 0x180D;

bool isVariantSelector(uint32_t charcode)
{
    return (
            (charcode >= VARIANT_SELECTOR_START && charcode <= VARIANT_SELECTOR_END) ||
            (charcode >= MONGOLIAN_VARIANT_SELECTOR_START && charcode <= MONGOLIAN_VARIANT_SELECTOR_END)
    );
}

int loadFont(const char* path)
{
    FT_Face face;
    int error = FT_New_Face(library, path, 0, &face);

    if (error == 0)
    {
        faces.push_back(face);
        //logInfo(LOGTAG, "Loaded font: " + std::string(path));
    }
    else
    {
        logInfo(LOGTAG, "Failed to to load font: " + std::string(path) + ", Error: " +
                        std::to_string(error));
    }

    //A failure of some fonts is expected
    // 01-30 10:11:10.954 15894-15894/com.nordskog.swiftkeyexi E/###: Failed to to load font: /system/fonts/NanumGothic.ttf, Error: 1
    // 01-30 10:11:10.955 15894-15894/com.nordskog.swiftkeyexi E/###: Failed to to load font: /system/fonts/DroidSansFallback.ttf, Error: 1
    // 01-30 10:11:10.955 15894-15894/com.nordskog.swiftkeyexi E/###: Failed to to load font: /system/fonts/MTLmr3m.ttf, Error: 1
    //These have apparently been removed from android, but still exist in the font list.

    //On some sytems, a failure of /a lot/ of fonts is expected.
    //For some reason, some builds of Lollipop (Such as the emulator) has a massive massive list of fonts,
    //but only includes about a fourth of them. Quite strange that.

    //Yet others (Kitkat emulator) have the fonts to render emoji, but don't use them.
    //This results in us thinking it can and is rendering the emoji, but it really isn't.
    //That probably won't ever happen an any real device though.

    return 0;

}

//Returns 1 if glyph found, 2 if glyph is bitmap. 0 if nothing found.
GlyphType findGlyph(uint32_t charcode)
{
    //Variant selectors don't always have renderable equivalents (sometimes do though).
    if (isVariantSelector(charcode))
        return GlyphType::NORMAL;

    for (int i = 0; i < faces.size(); i++)
    {
        uint glyph_index = FT_Get_Char_Index(faces[i], charcode);


        if (glyph_index != 0)
        {
           // logError("###", "Found glyph in font "+std::to_string(i)+": "+faces[i]->family_name+", index: "+std::to_string(glyph_index));

            //We need to detect bitmap glyphs so we can render them to cache and display the resulting bitmap instead of
            //relying on a TextView to do things for us. Swiftkey grinds to a complete halt if you
            //have more than a few bitmap characters (emoji) in a textview.

            //As of writing, things appear to be face-specific, ie we can just check if the face has
            //any bitmap strikes, and assume any glyphs in it will be bitmap ones.
            //I don't /think/ you can mix scalable characters and bitmaps in the same face yet.
            //The major exception to this is SVG Glyphs in OpenType, which are scalable, colored emoji.
            //Google uses their own thing though, and they'll probably continue to do so.
            // If the emoji panel gets really choppy in swiftkey (but not in exi), consider this the likely cause.
            if (faces[i]->available_sizes != nullptr)
            {
                return GlyphType::BITMAP;
            }

            return GlyphType::NORMAL;
        }

    }

    return GlyphType::NONE;
}

extern "C"
{

    JNIEXPORT void JNICALL Java_com_mayulive_swiftkeyexi_util_FontLoader_nInitFontLoader(JNIEnv* env, jobject  thiz, jobjectArray stringArray)
    {

       // std::vector<std::string> fonts = getFontFiles();

        //if (loadFunctions() == 0)
        {
            libraryInit = true;

            std::vector<const char*> fonts;

            int stringCount = env->GetArrayLength(stringArray);

            for (int i=0; i<stringCount; i++)
            {
                jstring string = (jstring) (env->GetObjectArrayElement(stringArray, i));
                const char *rawString = env->GetStringUTFChars(string, 0);
                fonts.push_back(rawString);
            }

            //FT_Init_FreeType_Function(&library);

            FT_Error error = FT_Init_FreeType(&library);
            if (error != 0)
            {
                logError(LOGTAG, "Failed to init FreeType: " + std::to_string(error));
                libraryInit = false;
                return;
            }

            for (const char* path : fonts)
            {
                loadFont(path);
            }

            logInfo(LOGTAG, "Font count: "+std::to_string(fonts.size()));

            for (int i=0; i<stringCount; i++)
            {
                jstring string = (jstring) (env->GetObjectArrayElement(stringArray, i));
                env->ReleaseStringUTFChars(string, fonts[i]);
            }

        }
    }

    JNIEXPORT jint JNICALL Java_com_mayulive_swiftkeyexi_util_FontLoader_nGetGlyphInfo(JNIEnv* env, jobject  thiz, jbyteArray string, jboolean returnOnUnrenderable)
    {
        if (libraryInit)
        {
            jbyte* text_input = env->GetByteArrayElements(string, NULL);
            jsize size = env->GetArrayLength(string);

            uint8_t* startPointer = (uint8_t*)text_input;

            char32_t buffer;
            int topState = 0;

            int i = 0;
            while (i < size)
            {
                //Why write my own utf-8 decoder? Because the only existing
                //solution I could find broke randomly. Exact same input every time,
                //random result every time.
                int traversed = utf8ToCodePoint(startPointer+i, buffer);

                //utf8ToCodePoint returns the codepoint as distinct bytes in their actual order
                //Truetype expects the bytes to be in reverse order. Endian shenanigans I guess.
                reverseChar(&buffer);

                GlyphType value = findGlyph(buffer);

                //Sometimes we just want to check if there are any bitmap characters,
                //other times if /everything/ is renderable
                if (returnOnUnrenderable && value == GlyphType::NONE)
                {
                    topState = GlyphType::NONE;
                    break;
                }

                if (value>topState)
                    topState = value;
                if (topState == GlyphType::BITMAP)
                    break;

                switch(traversed)
                {
                    case -1:
                    {
                        //Error
                        i = size; //Break outer loop
                        logError(LOGTAG, "Error parsing UTF-8 string");
                        break;
                    }

                    case 0:
                    {
                        i++;
                        break;
                        //Null byte. We know the length of string so treat as single byte.
                    }
                    default:
                    {
                        i += traversed;
                        break;
                    }
                }
            }

            env->ReleaseByteArrayElements(string, text_input, NULL);
            return topState;
        }
        else
        {
            return GlyphType::NORMAL;
        }



    }

}

