# Copyright 2018 (C) O.S. Systems Software LTDA.
SUMMARY = "Samples for OpenGL ES"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aeb969185a143c3c25130bc2c3ef9a50"
DEPENDS = "imx-gpu-viv zlib libpng procps"

APITRACE_SRC ?= "git://source.codeaurora.org/external/imx/apitrace-imx.git;protocol=https"
SRCBRANCH = "imx_8.0"
SRC_URI = "${APITRACE_SRC};branch=${SRCBRANCH}"
SRCREV = "179b913aee4e7d047c041940b426570f288de235" 

S = "${WORKDIR}/git"

inherit cmake pkgconfig perlnative pythonnative

PACKAGECONFIG     ??= "egl waffle"
PACKAGECONFIG_mx6 ??= "egl"
PACKAGECONFIG_mx7 ??= "egl"
PACKAGECONFIG_append = \
    "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
        bb.utils.contains('DISTRO_FEATURES',     'x11', ' x11', \
                                                        '', d), d)}"
PACKAGECONFIG_append_imxgpu2d = " vivante"

PACKAGECONFIG[egl] = "-DENABLE_EGL=ON,-DENABLE_EGL=OFF,virtual/egl"
PACKAGECONFIG[gui] = "-DENABLE_GUI=ON,-DENABLE_GUI=OFF"
PACKAGECONFIG[multiarch] = "-DENABLE_MULTIARCH=ON,-DENABLE_MULTIARCH=OFF"
PACKAGECONFIG[waffle] = "-DENABLE_WAFFLE=ON,-DENABLE_WAFFLE=OFF,waffle"
PACKAGECONFIG[x11] = "-DENABLE_X11=ON,-DENABLE_X11=OFF"
PACKAGECONFIG[vivante] = "-DENABLE_VIVANTE=ON,-DENABLE_VIVANTE=OFF,virtual/libg2d"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
FILES_${PN} += " \
    ${libdir}/apitrace/scripts/* \
    ${libdir}/apitrace/wrappers/* \
"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
COMPATIBLE_MACHINE = "(imxgpu)"
