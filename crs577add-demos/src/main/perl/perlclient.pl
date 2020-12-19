# /usr/bin/perl -w
# simple Perl client to access Web service
# from a previous version of the course -- needs
# updating to the Currency or Airport SOAP service.

$arg = @ARGV[0];

# the URL for the web service
$url = 'http://localhost:8080/axis/services/StringOpsService?WSDL';

# include the SOAP::Lite module
use SOAP::Lite;

print "\"$arg\" in uppercase is \"";

# Invoke the service:
print SOAP::Lite->service($url)->toUpper($arg);

print "\"\n";

# The following statement prints the entire SOAP response message
# print SOAP::Lite->service($url)->outputxml(1)->toUpper($arg), "\n";

=pod

Synopsis:
    perl perlclient.pl "Hello world!"

    Output: 
    HELLO WORLD!

Or skip the script completely and access the service from the command line:
    perl "-MSOAP::Lite service=>'http://localhost:8080/axis/services/StringOpsService?WSDL'" -le "print toUpper('Hello world!')"

Original by: marc.weinmann.lwed@statefarm.com. Hacked on by the lt577 team.

=cut
