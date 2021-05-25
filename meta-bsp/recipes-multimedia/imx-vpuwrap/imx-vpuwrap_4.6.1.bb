# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2021 NXP
# Released under the MIT license (see COPYING.MIT for the terms)
DESCRIPTION = "Freescale Multimedia VPU wrapper"
DEPENDS = "virtual/imxvpu"
DEPENDS_append_mx8mp = " imx-vpu-hantro-vc"
LICENSE = "Proprietary"
SECTION = "multimedia"
LIC_FILES_CHKSUM = "file://COPYING;md5=85d06b38f17b935ca11784d67075b846"

IMX_VPUWRAP_SRC ?= "git://github.com/NXP/imx-vpuwrap.git;protocol=https"
SRCBRANCH = "MM_04.06.01_2105_L5.10.y"
SRC_URI = "${IMX_VPUWRAP_SRC};branch=${SRCBRANCH}"
SRCREV = "cf94b657b7447d3ee496b548d9ed8084dd24920b"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_install_append() {
    # FIXME: Drop examples for now
    rm -r ${D}${datadir}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Compatible only for i.MX with VPU
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_imxvpu = "${MACHINE}"