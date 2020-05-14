DESCRIPTION = "Cross-platform, Serial Port library written in C++"
SECTION = "lib"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=51;endline=62;md5=099771be34bf8e944de2980f694e0e2e"

SRC_URI = "https://github.com/wjwwood/serial/archive/${PV}.tar.gz \
	 file://0001-evolution-for-dynamic-shared-library-and-dllexport-under-Windows.patch \
	 file://0002-removing-debug-compiler-options.patch \
	 file://0003-compilation-done-on-windows-side.patch \
	"

PR = "r0"

S="${WORKDIR}/serial-${PV}"

SRC_URI[md5sum] = "b6d9ebdf821654715656577652b61b64"
SRC_URI[sha256sum] = "0c2a789ce485a83ed640c777a7d1cd1256976890ece4c126f93751a08643917a"

CXXFLAGS += " -Iinclude -fPIC -pedantic"

RPROVIDES_${PN}="libserial.so"

INHIBIT_PACKAGE_DEBUG_SPLIT="1"

do_configure() {
	rm -f "${S}"/CMakeLists.txt	# Remove cmake's files to force compilation using a normal Makefile
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
