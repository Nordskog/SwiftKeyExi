//
// Created by Roughy on 1/29/2017.
//

#ifndef SWIFTKEYEXI_LOG_H
#define SWIFTKEYEXI_LOG_H

#include <android/log.h>
#include <string>

int logError(std::string tag, std::string message)
{
    const char* string = message.c_str();

    __android_log_print(ANDROID_LOG_ERROR,tag.c_str(),string,"%f");

    return 0;
}

int logInfo(std::string tag, std::string message)
{
    const char* string = message.c_str();

    __android_log_print(ANDROID_LOG_INFO,tag.c_str(),string,"%f");

    return 0;
}


#endif //SWIFTKEYEXI_LOG_H
