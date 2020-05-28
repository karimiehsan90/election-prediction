#!/usr/bin/env bash

set -o errexit

USER=${1}

mkdir -p ~/vagrant

wget http://main:8080/vagrant_2.2.5_x86_64.deb -O ~/vagrant/vagrant.deb

dpkg -i ~/vagrant/vagrant.deb

apt-get build-dep vagrant ruby-libvirt
apt-get install qemu libvirt-bin ebtables dnsmasq-base
apt-get install libxslt-dev libxml2-dev libvirt-dev zlib1g-dev ruby-dev
apt-get install ca-certificates

vagrant plugin install vagrant-libvirt

mkdir -p /etc/pki/CA

cp cacert.pem /etc/pki/CA/

usermod -aG libvirt ${USER}
