# Copyright (C) 2020-2021 NXP
# Released under the MIT license (see COPYING.MIT for the terms)
# The recipe is licensed under MIT (see COPYING.MIT for the terms)

DESCRIPTION = "Sound Open Firmware with Zephyr"
HOMEPAGE = "https://www.sofproject.org"
SECTION = "kernel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE;md5=0f00d99239d922ffd13cabef83b33444"

SRC_URI = "${FSL_MIRROR}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "4e42585af926adf4c74945027169b140"
SRC_URI[sha256sum] = "fc223d8cb06a59adda7cdaf98a13200f6a2d51756b9e0511157bd06fd380a6fc"

inherit allarch

do_install() {
    # Install firmware image 
    install -d ${D}${nonarch_base_libdir}/firmware/imx/sof
    cp -r imx/sof/* ${D}${nonarch_base_libdir}/firmware/imx/sof
}

FILES_${PN} = "${nonarch_base_libdir}/firmware/imx/sof"

