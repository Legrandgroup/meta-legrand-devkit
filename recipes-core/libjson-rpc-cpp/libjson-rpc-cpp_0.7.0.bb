SUMMARY = "C++ framework for json-rpc (json remote procedure call)"
SECTION = "lib"
HOMEPAGE = "https://github.com/cinemast/libjson-rpc-cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;beginline=2;md5=495acfc0327bba6d760e33900e1e1465"

RDEPENDS_${PN} = "jsoncpp"
DEPENDS = "jsoncpp"

SRC_URI[md5sum] = "6f17018d2f09227ad2855b48e4b7a844"
SRC_URI[sha256sum] = "669c2259909f11a8c196923a910f9a16a8225ecc14e6c30e2bcb712bab9097eb"

SRC_URI = "https://github.com/cinemast/libjson-rpc-cpp/archive/v${PV}.tar.gz \
	 file://0001-Adding-support-for-pkg-config.patch \
	 file://0005-Add-include-for-musl.patch \
	 "

S = "${WORKDIR}/libjson-rpc-cpp-${PV}"

inherit cmake pkgconfig

do_configure() {
	cmake ${S} -DHTTP_SERVER=NO -DHTTP_CLIENT=NO -DCOMPILE_EXAMPLES=NO -DCOMPILE_TESTS=NO -DCOMPILE_STUBGEN=NO -DUNIX_DOMAIN_SOCKET_SERVER=YES -DUNIX_DOMAIN_SOCKET_CLIENT=YES -DJSONCPP_INCLUDE_DIR="${STAGING_INCDIR}" -DJSONCPP_LIBRARY="${STAGING_LIBDIR}" -DCMAKE_INCLUDE_PATH="${STAGING_INCDIR}" -DCMAKE_INSTALL_PREFIX:PATH=${prefix}
}

do_configure_class-native() {
	cmake ${S} -DHTTP_SERVER=NO -DHTTP_CLIENT=NO -DCOMPILE_EXAMPLES=NO -DCOMPILE_TESTS=NO -DCOMPILE_STUBGEN=YES -DUNIX_DOMAIN_SOCKET_SERVER=NO -DUNIX_DOMAIN_SOCKET_CLIENT=NO -DJSONCPP_INCLUDE_DIR="${includedir}" -DCMAKE_INCLUDE_PATH="${includedir}" -DCMAKE_INSTALL_PREFIX:PATH=${prefix}
}

BBCLASSEXTEND = "native"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
PACKAGES_class-native = "${PN} ${PN}-dev ${PN}-dbg"
