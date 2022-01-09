# COMMAND LINE KARAF
- feature:repo-add mvn:karaf.person/person-features/LATEST/xml
- feature:install person-project-feature
# RESTful services
- http://localhost:8181/cxf/