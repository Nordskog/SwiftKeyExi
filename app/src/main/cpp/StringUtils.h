//
// Created by Roughy on 2/1/2017.
//

#ifndef SWIFTKEYEXI_STRINGUTILS_H
#define SWIFTKEYEXI_STRINGUTILS_H



#include <iostream>
#include <stdint.h>
#include <string>
#include <cstring>
#include <stdio.h>
#include <bitset>
#include <tgmath.h>

#include <algorithm>

using namespace std;

template <typename T> void printBits(T memory)
{
    stringstream ss;
    std::bitset<sizeof(T)*8> x( memory );
    ss << x << endl;

    logError("###", ss.str());
}

template <typename T> void printHex(T memory)
{
    std::cout << std::hex << memory << endl;
}


void printChar(char32_t& memory)
{
    uint8_t* pointer = (uint8_t*)&memory;

    for (int i = 0; i < 4; i++)
    {
        printBits(*(pointer+i));
    }
}

void printCharHex(char32_t& memory)
{
    uint8_t* pointer = (uint8_t*)&memory;

    for (int i = 0; i < 4; i++)
    {
        printHex(*(pointer+i));
    }
}


int getByteCount(uint8_t memory)
{
    if ( ( memory & (1 << 7) )  == 0 )
    {
        //Single byte
        return 1;
    }
    else
    {
        if ( ( memory & (1 << 6) )  == 0)
        {
            //Continuing byte
            return 0;
        }
        else
        {
            for (int i = 0; i < 4; i++)
            {
                if ( ( memory & (1 << (6-i)) )  == 0)
                {
                    return i+1;
                }
            }
        }
    }

    return -1; //Error
}

bool isNull(uint8_t* memory)
{
    return (*memory == 0x00);
}


void reverseChar(char32_t* character)
{
    std::reverse((uint8_t*)character, (uint8_t*)character+4);
}


//Parses pointer and fills buffer with result.
//Returns number of bytes parsed, or 0 if null,
//If input string does not contain null, it will continue
//until an invalid utf8 byte is encountered.
//The data is still copied into the buffer, so if you know
//how long the string is, you may continue after the null
int utf8ToCodePoint(uint8_t* pointer, char32_t& buffer)
{


    char32_t singleBuffer;
    memset(&buffer, 0,4);

    int byteCount = getByteCount(*pointer);

   // printBits(*pointer);
    //logError("###", "Bytecount: "+std::to_string(byteCount) );


    if (byteCount < 1)	//Error
        return -1;

    if ( isNull(pointer) )
    {
        //Copy to last byte
        memcpy(( (uint8_t*)&buffer)+3, pointer,1);
        return 0;
    }

    if (byteCount == 1)	//Single byte
    {
        //Copy to last byte
        memcpy(( (uint8_t*)&buffer)+3, pointer,1);
        return 1;
    }
    else if (byteCount != 0)	//Start byte
    {

        int pos = 0;

        memcpy(&buffer, pointer,1);

        //If you have two or more bytes, the bits are effectively ordered
        //[8,9,10,11,12,13,14,15  0,1,2,3,4,5,6,7] when applying
        //bitwise operators. Reverse byte order first and return when
        //we're done.
        reverseChar(&buffer);

        buffer = buffer << (byteCount+1);
        pos += 7 - byteCount;

        for (int j = 0; j < byteCount-1; j++)
        {
            //std::cout << "continuing bytecount: " << std::to_string(getByteCount( pointer[j+1] )) << endl;
            if (getByteCount( pointer[j+1] ) != 0)
            {
                //Not a continuing byte ... welp
               // std::cout << "continuing error" << endl;
                return -1;
            }

            memset(&singleBuffer, 0,4);
            memcpy(&singleBuffer, &pointer[j+1],1);

            reverseChar(&singleBuffer);

            singleBuffer = (singleBuffer << 2) >> pos;

            buffer = buffer | singleBuffer;

            pos += 6;
        }

        //pos -= 6;
        buffer = buffer >> ( (8*4) - (pos) );

        reverseChar(&buffer);

       // printChar(buffer);

        return byteCount;
    }

    return -1;
}





#endif //SWIFTKEYEXI_STRINGUTILS_H
