SUMMARY = "JsonCpp is a C++ library that allows manipulating JSON values"
DESCRIPTION = "JSON is a lightweight data-interchange format. It can represent numbers, strings, ordered sequences of values, and collections of name/value pairs. JsonCpp is a C++ library that allows manipulating JSON values, including serialization and deserialization to and from strings. It can also preserve existing comment in unserialization/serialization steps, making it a convenient format to store user input files."
SECTION = "lib"
HOMEPAGE = "https://github.com/open-source-parsers/jsoncpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c56ee55c03a55f8105b969d8270632ce"

SRC_URI = "https://github.com/open-source-parsers/jsoncpp/archive/${PV}.tar.gz \
	 "

SRC_URI[md5sum] = "f4cda66a1c4b6ab9427025026c578aa7"
SRC_URI[sha256sum] = "0d83eb493d70daba95fe20f9e63b131eb56ed4e2e9013fc6093a54eddcc72236"

inherit cmake pkgconfig

EXTRA_OECMAKE = '-DBUILD_SHARED_LIBS=ON \
	-DJSONCPP_LIB_BUILD_SHARED=ON \
	-DJSONCPP_WITH_TESTS=OFF \
	-DJSONCPP_WITH_PKGCONFIG_SUPPORT=ON \
	'

BBCLASSEXTEND = "native"

FILES_${PN} = "${libdir}/libjsoncpp.so.${PV} \
	 ${libdir}/libjsoncpp.so.0 \
	 "
