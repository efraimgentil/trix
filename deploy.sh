

mvn clean package -P openshift
sleep 2
rhc scp trix upload target/ROOT.war wildfly/standalone/deployments/
 

