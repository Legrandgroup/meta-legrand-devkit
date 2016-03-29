require smarty.inc

S = "${WORKDIR}/smarty-${PV}"

SRC_URI += "https://github.com/smarty-php/smarty/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "232498146b37024eb72eef306a38f87e"
SRC_URI[sha256sum] = "0f1bb61bbc9dd0b01e1adedc9c5e54f95c37c1eb2016b1c73b0e60ceda911d6d"
