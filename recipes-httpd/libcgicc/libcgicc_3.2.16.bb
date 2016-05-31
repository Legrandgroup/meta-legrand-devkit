require libcgicc.inc
EXTRA_OECONF += " --disable-demos --htmldir=${docdir}"

SRC_URI += " file://libcgicc-3.2.16-no-make-doc.patch"


SRC_URI[md5sum] = "ca7c21e8ad34989ec6252d9c255e5465"
SRC_URI[sha256sum] = "9dceb36d2da52791ee11f2fb5bb6435c22e69ef50f38073c4329386b883d5aaa"
