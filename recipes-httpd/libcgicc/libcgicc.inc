DESCRIPTION = "a C++ class library for writing CGI applications"
HOMEPAGE = "http://www.gnu.org/software/cgicc/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=60627c3fa530266bd8c2d1f15b554b86"

SRC_URI = "ftp://ftp.gnu.org/gnu/cgicc/cgicc-${PV}.tar.gz"

S = "${WORKDIR}/cgicc-${PV}"

inherit autotools
