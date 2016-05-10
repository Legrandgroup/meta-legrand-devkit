SUMMARY = "C++ framework for json-rpc (json remote procedure call)"
SECTION = "lib"
HOMEPAGE = "https://github.com/cinemast/libjson-rpc-cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f1b1426570d897385522252fef4572b0"

RDEPENDS_${PN} = "jsoncpp"
DEPENDS = "jsoncpp"

SRCREV="4066b2b376826ca16aad7e2b6d2ee457ed406869"

SRC_URI = "git://github.com/cinemast/libjson-rpc-cpp.git;protocol=http \
	 file://0001-Adding-support-for-pkg-config.patch \
	 file://0002-Adding-example-code-for-UNIX-socket-client-and-serve.patch \
	 file://0003-Re-integrating-Alex-Poirot-s-original-code-not-merge.patch \
	 "

S = "${WORKDIR}/git/"

inherit cmake pkgconfig

do_configure() {
	cmake ${S} -DHTTP_SERVER=NO -DHTTP_CLIENT=NO -DCOMPILE_EXAMPLES=NO -DCOMPILE_TESTS=NO -DCOMPILE_STUBGEN=NO -DUNIX_DOMAIN_SOCKET_SERVER=YES -DUNIX_DOMAIN_SOCKET_CLIENT=YES -DJSONCPP_INCLUDE_DIR="${STAGING_INCDIR}" -DJSONCPP_LIBRARY="${STAGING_LIBDIR}" -DCMAKE_INCLUDE_PATH="${STAGING_INCDIR}" -DCMAKE_INSTALL_PREFIX:PATH=${prefix}
}

do_configure_class-native() {
	cmake ${S} -DHTTP_SERVER=NO -DHTTP_CLIENT=NO -DCOMPILE_EXAMPLES=NO -DCOMPILE_TESTS=NO -DCOMPILE_STUBGEN=YES -DUNIX_DOMAIN_SOCKET_SERVER=NO -DUNIX_DOMAIN_SOCKET_CLIENT=NO -DCMAKE_INSTALL_PREFIX:PATH=${prefix}
}

BBCLASSEXTEND = "native"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
PACKAGES_class-native = "${PN} ${PN}-dev ${PN}-dbg"
