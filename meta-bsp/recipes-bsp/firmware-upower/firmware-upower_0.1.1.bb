# Copyright 2021 NXP
DESCRIPTION = "NXP i.MX uPower firmware"
LICENSE = "Proprietary"
SECTION = "BSP"
LIC_FILES_CHKSUM = "file://COPYING;md5=e565271ec9a80ce47abbddc4bffe56fa" 

inherit fsl-eula2-unpack2 pkgconfig deploy

SRC_URI[md5sum] = "4f6bcc2e9180f95896ae9381c5ef3f32"
SRC_URI[sha256sum] = "c7b18e0cd260f8a1ceb05ffe756188831f803b722063bd7006f9216833ce571d"

do_configure[noexec] = "1"

do_compile[noexec] = "1"

do_install[noexec] = "1"

BOOT_TOOLS = "imx-boot-tools"

do_deploy () {
    # Deploy the related firmware to be package by imx-boot
    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0644 ${S}/upower.bin  ${DEPLOYDIR}/${BOOT_TOOLS}
}
addtask deploy after do_install before do_build

COMPATIBLE_MACHINE = "(mx8ulp)"