# Copyright 2020-2021 NXP
DESCRIPTION = "i.MX Neural Networks Accelerator Plugin"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=bba6cdb9c2b03c849ed4975ed9ed90dc"

DEPENDS = "imx-gpu-viv"
NN_IMX_SRC ?= "git://source.codeaurora.org/external/imx/nn-imx.git;protocol=https"
SRCBRANCH = "nn_1.3.y"

SRCREV = "affd7629e00f7d2223e052aa82d5ea1be727af4c"

SRC_URI = "${NN_IMX_SRC};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "SDKTARGETSYSROOT=${STAGING_DIR_HOST} \
                 AQROOT=`pwd` \
"
TARGET_CC_ARCH += "${LDFLAGS}"

do_install () {
    install -d ${D}${libdir}
    install -d ${D}${includedir}/OVXLIB
    install -d ${D}${includedir}/nnrt
    cp -d ${S}/*.so* ${D}${libdir}
    cp -r ${S}/include/OVXLIB/* ${D}/${includedir}/OVXLIB
    cp -r ${S}/include/nnrt/* ${D}/${includedir}/nnrt
}

# libneuralnetworks.so is dynamically loaded and thus needed in the
# main package
FILES:${PN}-dev:remove = "${libdir}/lib*.so"
FILES:${PN} += "${libdir}/libneuralnetworks${SOLIBSDEV}"
FILES:${PN}-dev += " \
    ${libdir}/libnnrt${SOLIBSDEV} \
    ${libdir}/libovxlib${SOLIBSDEV} \
"
INSANE_SKIP:${PN} += "dev-so"

# Works for i.MX 8 with GPU except for 8M Mini
COMPATIBLE_MACHINE        = "(^$)"
COMPATIBLE_MACHINE:imxgpu = "(mx8)"
COMPATIBLE_MACHINE:mx8mm  = "(^$)"