# Flippy-tester

A very simple project for creating a standalone "Flippy" service (see
[giftig/flippy](https://www.github.com/giftig/flippy/)) and testing its
interface and an admin interface using docker, nginx, and redis.

Contains a docker-compose file which will bring up the three relevant services,
serving the main flippy interface on port 9001 and the nginx interface on port
9002, the latter of which is configured to allow CORS openly, allowing easy
testing of an interface cross-domain.
