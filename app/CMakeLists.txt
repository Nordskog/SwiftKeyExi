# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.4.1)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.








set(BUILD_SHARED_LIBS false)
add_subdirectory(lib/freetype-2.8)
include_directories(lib/freetype-2.8/include)




############################
# Internal libraries below #
############################

project(FontLoader)


add_library( # Specifies the name of the library.
             FontLoader

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/FontLoader.cpp )

# Specifies a path to native header files.
include_directories(src/main/cpp/include/)

find_library( log-lib log )


target_link_libraries( # Specifies the target library.
                       FontLoader

                       # Links the log library to the target library.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.
                       FontLoader

                       # Links the log library to the target library.
                       freetype )