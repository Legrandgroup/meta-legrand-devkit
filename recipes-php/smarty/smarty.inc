DESCRIPTION = "Smarty PHP template engine"
HOMEPAGE = "http://www.smarty.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.lib;md5=bb3ca60759f3202f1ae42e3519cd06bc"
SECTION = "net"

PR = "r1"

DIRFILES = "1"
SRC_URI = "file://99_smarty"

inherit useradd

PHP_LIBDIR = "${libdir}/php5"

do_install () {
	install -d ${D}${PHP_LIBDIR}/smarty/
	cp -R ${S}/libs/ ${D}${PHP_LIBDIR}/smarty/
	
	install -d ${D}${localstatedir}/www/localhost/
	install -d ${D}${localstatedir}/volatile/www/templates_c/	
	ln -s ${localstatedir}/volatile/www/templates_c ${D}${localstatedir}/www/localhost/
	
	chown www:www-data -R ${D}${localstatedir}
	
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 0644 ${WORKDIR}/99_smarty ${D}${sysconfdir}/default/volatiles/
	sed -i 's,/var/,${localstatedir}/,g' ${D}${sysconfdir}/default/volatiles/99_smarty
}

pkg_postinst_${PN} () {
	if [ -z "$D" ]; then
		if type systemd-tmpfiles >/dev/null; then
			systemd-tmpfiles --create
		elif [ -e ${sysconfdir}/init.d/populate-volatile.sh ]; then
			${sysconfdir}/init.d/populate-volatile.sh update
		fi
	fi
}

FILES_${PN} = "${sysconfdir}/default/volatiles/99_smarty \
	       ${PHP_LIBDIR}/smarty/* \
	       ${localstatedir}/volatile/www/templates_c \
	       ${localstatedir}/www/localhost/* "

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --system --no-create-home \
    --home ${localstatedir}/www/localhost \
    --groups www-data \
    --user-group www"
