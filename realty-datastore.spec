# if you rebuild, please change bugtracker_url accordingly:

Name: realty-datastore
Summary: EDR REST datastore service
Version: %{version}
Release: %{release_no}%{?dist}
BuildArch: noarch
License: Ambita
URL: http://p1cstorage:9000
Group: Applications/System
Buildroot: %{_tmppath}/%{name}-%{version}-%{release}-root-%(%__id_u -n)
AutoReqProv: no

Requires(pre): shadow-utils

%define debug_package %{nil}
%define __jar_repack %{nil}

%description
EDR REST service

%prep

%build
pwd
FILE_VERSION=$(ls -1 ../../target/universal/%{name}*.zip |egrep "/%{name}[^/]*zip" -o  |sed -e "s/\.zip$//" |sed -e "s|/%{name}\-||")
echo $FILE_VERSION
rm -rf %{name}-${FILE_VERSION}

unzip ../../target/universal/%{name}-${FILE_VERSION}.zip
cd %{name}-${FILE_VERSION}
mv bin/realty-datastore bin/start
rm bin/realty-datastore.bat
rm -f conf/application.conf conf/logger.xml
cd ..

%install
FILE_VERSION=$(ls -1 ../../target/universal/%{name}*.zip |egrep "/%{name}[^/]*zip" -o  |sed -e "s/\.zip$//" |sed -e "s|/%{name}\-||")

mkdir -p %{buildroot}/opt/play
cp -R %{name}-${FILE_VERSION}/* %{buildroot}/opt/play/
mkdir -p %{buildroot}/var/log/play
mkdir -p %{buildroot}/var/run/play
ln -s ../application.conf %{buildroot}/opt/play/conf/application.conf
ln -s ../logger.xml %{buildroot}/opt/play/conf/logger.xml

%clean
rm -rf %{buildroot}

%pre
getent group play >/dev/null || groupadd -r play
getent passwd play >/dev/null || \
	useradd -r -g play -d /opt/play -s /sbin/false \
		-c "Play service account" play
exit 0

%files
%defattr(0444,root,root, 0555)
/opt/play
%attr(0555,root,root) /opt/play/bin/start
%attr(0755,play,root) /var/log/play
%attr(0755,play,root) /var/run/play

%changelog
* Wed May 11 2016 Anders H.Abrahamsen <aab@ambita.com>
- Copied from realty
