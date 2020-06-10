DESCRIPTION = "Cross-platform, Serial Port library written in C++"
HOMEPAGE = "https://wjwwood.github.com/serial/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=59;endline=65;md5=c3da3fd95b8eba82c3e7d97c7b91d462"
AUTHOR="Nicolas Gillen <nicolas.gillen@legrand.fr>"

SRC_URI = "git://github.com/wjwwood/serial.git;branch=master \
           file://0001-evolution-for-dynamic-shared-library-and-dllexport-under-Windows.patch \
           file://0002-compilation-done-on-windows-side.patch \
           "

SRCREV = "${AUTOREV}"
PV = "1.2.1+git${SRCPV}"

S="${WORKDIR}/git"

CXXFLAGS += " -Iinclude -fPIC -pedantic"

RPROVIDES_${PN}="libserial.so"

INHIBIT_PACKAGE_DEBUG_SPLIT="1"

do_configure() {
    rm -f "${S}"/CMakeLists.txt # Remove cmake's files to force compilation using a normal Makefile
    cp Makefile.linux-nocmake Makefile
}

do_install() {
    install -d "${D}${libdir}"
    install -m 0755 "${S}"/libserial.so "${D}${libdir}"
    
    install -d "${D}${includedir}"/serial
    install -d "${D}${includedir}"/serial/impl
    install -m 0755 "${S}"/include/serial/serial.h "${D}${includedir}"/serial/
    install -m 0755 "${S}"/include/serial/serial_api.h "${D}${includedir}"/serial/
    install -m 0755 "${S}"/include/serial/v8stdint.h "${D}${includedir}"/serial/
    install -m 0755 "${S}"/include/serial/impl/unix.h "${D}${includedir}"/serial/impl/
}

FILES_${PN} = "${libdir}/libserial.so \
               "

FILES_${PN}-dev = "${includedir}/serial/serial.h \
                   ${includedir}/serial/serial_api.h \
                   ${includedir}/serial/v8stdint.h \
                   ${includedir}/serial/impl/unix.h \
                   "
