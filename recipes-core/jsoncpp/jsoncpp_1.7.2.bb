SUMMARY = "JsonCpp is a C++ library that allows manipulating JSON values"
DESCRIPTION = "JSON is a lightweight data-interchange format. It can represent numbers, strings, ordered sequences of values, and collections of name/value pairs. JsonCpp is a C++ library that allows manipulating JSON values, including serialization and deserialization to and from strings. It can also preserve existing comment in unserialization/serialization steps, making it a convenient format to store user input files."
SECTION = "lib"
HOMEPAGE = "https://github.com/open-source-parsers/jsoncpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c56ee55c03a55f8105b969d8270632ce"

SRC_URI = "https://github.com/open-source-parsers/jsoncpp/archive/${PV}.tar.gz \
	 "

SRC_URI[md5sum] = "3989402269147d1f853b57c542037536"
SRC_URI[sha256sum] = "2179a7df19c1c6dc87e02c65b847efc914625a9b87df3e443d9610fc70c0f557"

inherit cmake pkgconfig

EXTRA_OECMAKE = '-DBUILD_SHARED_LIBS=ON \
	-DJSONCPP_LIB_BUILD_SHARED=ON \
	-DJSONCPP_WITH_TESTS=OFF \
	-DJSONCPP_WITH_PKGCONFIG_SUPPORT=ON \
	'

BBCLASSEXTEND = "native"

FILES_${PN} = "${libdir}/libjsoncpp.so* \
	 "
