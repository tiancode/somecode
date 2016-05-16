TARGET_PLATFORM := android-23
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := HelloLib
LOCAL_SRC_FILES += Hello.cpp
LOCAL_ARM_MODE := arm
COMMON_CFLAGS := -Werror -DANDROID -DDISABLE_IMPORTGL
ifeq ($(TARGET_ARCH),x86)
  LOCAL_CFLAGS := $(COMMON_CFLAGS)
else
  LOCAL_CFLAGS := -mfpu=vfp -mfloat-abi=hard -mhard-float -fno-short-enums -D_NDK_MATH_NO_SOFTFP=1 $(COMMON_CFLAGS)
endif
LOCAL_LDLIBS := -llog -lGLESv2 -Wl,-s
LOCAL_CPPFLAGS += -std=gnu++11
include $(BUILD_SHARED_LIBRARY)
