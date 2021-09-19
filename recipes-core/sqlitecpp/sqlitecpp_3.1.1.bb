DESCRIPTION = "C++ Wrapper for SQLite3."
SECTION = "lib"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a6abb8f9ddcf3b84c6995c2de97a2f1c"

PR = "r0"

DEPENDS = "sqlite3"

inherit cmake

SRC_URI = "git://github.com/SRombauts/SQLiteCpp.git;protocol=http;tag=${PV} \
	 file://0001-install-dynamic-library.patch \
	 file://0002-add-version-and-change-soversion.patch \
	 "

S = "${WORKDIR}/git/"

# Do not use the embedded sqlite3 code, use it from sqlite3 package we depend on
EXTRA_OECMAKE = '-DSQLITECPP_INTERNAL_SQLITE=OFF'

do_install_prepend() {
	dollar='$'	# We need to protect dollar below in order to avoid bitbake to perform expansion of libdir and includedir variables in the file
	# We will forge a package config file (.pc) manually here as cmake does not provide one for us
	cat > ${S}sqlitecpp.pc << EOF
libdir=${libdir}
includedir=${includedir}/SQliteCpp
 
Name: ${PN}
Description: ${DESCRIPTION}
Version: ${PV}
Libs: -L$dollar{libdir} -lSQLiteCpp -lsqlite3
Libs.private: 
Cflags:  -I$dollar{includedir}
EOF
	install -d ${D}${libdir}/pkgconfig
	install -m 0755 ${S}sqlitecpp.pc ${D}${libdir}/pkgconfig/
}

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
FILES_${PN} = "${libdir}/*.so.*"
FILES_${PN}-dev = "${includedir}/SQLiteCpp/* ${libdir}/*.so ${libdir}/pkgconfig/*.pc ${libdir}/cmake/* "
FILES_${PN}-dbg = "${libdir}/.debug/*"
