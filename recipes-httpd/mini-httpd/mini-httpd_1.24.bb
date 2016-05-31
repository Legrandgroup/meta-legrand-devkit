# Copyright (C) 2009 Khem Raj <raj.khem@gmail.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "mini_httpd is a small HTTP server. \
	       It implements all the basic features of an HTTP server \
	       It can also be configured to do SSL/HTTPS and IPv6. \
	       "
HOMEPAGE = "http://www.acme.com/software/mini_httpd/"
LICENSE = "BSD-2-Clause"
SECTION = "net"
DEPENDS = "openssl"
LIC_FILES_CHKSUM = "file://mini_httpd.c;beginline=6;endline=25;md5=d99204103222893625ccef9ec2ce9239"
PR = "r0"

SRC_URI="http://www.acme.com/software/mini_httpd/mini_httpd-${PV}.tar.gz \
	 file://new-bindir-mandir.patch \
	 file://remove-CC.patch \
	 file://mini-httpd.conf \
	 file://init \
	"

SRC_URI[md5sum] = "8524ee6da83078b0ba05d75ef17d08e1"
SRC_URI[sha256sum] = "ba3846368f1c42b10931b5c25199b011d243dbf8025e51e3e2c2919952f6c359"

INITSCRIPT_NAME = "mini_httpd"
INITSCRIPT_PARAMS = "defaults"

S = "${WORKDIR}/mini_httpd-${PV}"

inherit update-rc.d

PACKAGECONFIG ??= "openssl"

# This is here to stop bitbake from complaining about QA Issue during do_configure() tasl, but it is actually during do_compile() task that we take care of openssl option
PACKAGECONFIG[openssl] = "--with-openssl"

EXTRA_OEMAKE="'${@bb.utils.contains('PACKAGECONFIG', 'openssl', 'SSL_DEFS=-DUSE_SSL', '', d)}' '${@bb.utils.contains('PACKAGECONFIG', 'openssl', 'SSL_LIBS=-lssl -lcrypto', '', d)}'"

INSANE_SKIP_${PN} = "already-stripped"

do_install () {
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/mini-httpd
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/mini_httpd
	install -m 0755 ${WORKDIR}/mini-httpd.conf ${D}${sysconfdir}/mini-httpd.conf
	oe_runmake 'BINDIR=${D}${sbindir}' 'MANDIR=${D}${mandir}' install
}
