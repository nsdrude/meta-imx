SUMMARY = "Tensor Interface Module for OpenVX"
DESCRIPTION = "TIM-VX is a software integration module provided by VeriSilicon \
to facilitate deployment of Neural-Networks on OpenVX enabled ML accelerators. It serves as \
the backend binding for runtime frameworks such as Android NN, Tensorflow-Lite, MLIR, TVM and more."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ededf2503f5d147ae718276dfd28801f"

DEPENDS = "imx-gpu-viv nn-imx"

TIM_VX_SRC ?= "git://source.codeaurora.org/external/imx/tim-vx-imx.git;protocol=https"
SRCBRANCH = "lf-5.10.52_2.1.0"
SRCREV = "8ce6c611e6a45feff0326b7f1fdf7c5e553f7f6d"
SRC_URI = "${TIM_VX_SRC};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE =  " \
    -DCONFIG=YOCTO \
    -DTIM_VX_ENABLE_TEST=off \
    -DTIM_VX_USE_EXTERNAL_OVXLIB=on \
    -DOVXLIB_INC=${PKG_CONFIG_SYSROOT_DIR}/usr/include/OVXLIB \
"

# Output library is unversioned
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

COMPATIBLE_MACHINE          = "(^$)"
COMPATIBLE_MACHINE_imxgpu3d = "(mx8)"
COMPATIBLE_MACHINE_mx8mm    = "(^$)"