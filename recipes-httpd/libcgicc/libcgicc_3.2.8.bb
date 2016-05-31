require libcgicc.inc
EXTRA_OECONF += " --disable-demos --htmldir=${docdir}"

SRC_URI[md5sum] = "7bfe01649f2b7bf582a8d0034ed0b32b"
SRC_URI[sha256sum] = "8718e1b9919b6381d55ef7ebca42564278b76225e83ab94db99cee18668e4033"

do_configure_prepend() {
  cd "${S}"
  rm -f config.log config.status
  find ./ -name 'Makefile' -print0|xargs -0 rm
}

do_install_prepend() {
  cd "${S}"
}

#do_install () {
#	install -d ${STAGING_INCDIR}/cgicc
#	cp -LR cgicc/*.h ${STAGING_INCDIR}/cgicc
#	oe_libinstall -C cgicc libcgicc ${STAGING_LIBDIR}
#}
