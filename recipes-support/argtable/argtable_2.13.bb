SUMMARY = "Argtable is an ANSI C library for parsing GNU style command line options"
SECTION = "libs"
HOMEPAGE = "http://argtable.sourceforge.net/"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=f30a9716ef3762e3467a2f62bf790f0a"
BBCLASSEXTEND = "native"

PROVIDES = "libargtable2"

SRC_URI = "${SOURCEFORGE_MIRROR}/argtable/argtable2-13.tar.gz"

SRC_URI[md5sum] = "156773989d0d6406cea36526d3926668"
SRC_URI[sha256sum] = "8f77e8a7ced5301af6e22f47302fdbc3b1ff41f2b83c43c77ae5ca041771ddbf"

S = "${WORKDIR}/argtable2-13"

inherit autotools

EXTRA_OECONF = "--enable-static=no"
