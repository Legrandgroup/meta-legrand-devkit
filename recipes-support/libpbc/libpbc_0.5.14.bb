DESCRIPTION = "Free C library built on the GMP library that performs the mathematical operations underlying pairing-based cryptosystems."
HOMEPAGE = "https://crypto.stanford.edu/pbc/"
SECTION = "libs"
LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"
AUTHOR="Nicolas Gillen <nicolas.gillen@legrand.fr>"

DEPENDS = "gmp"

SRC_URI = "https://crypto.stanford.edu/pbc/files/pbc-${PV}.tar.gz \
           file://0001-fix-include-folder.patch \
           "

S="${WORKDIR}/pbc-${PV}"

inherit autotools

SRC_URI[md5sum] = "842b85c50ba1464b207fc7b7a1f6ac18"
SRC_URI[sha256sum] = "772527404117587560080241cedaf441e5cac3269009cdde4c588a1dce4c23d2"
