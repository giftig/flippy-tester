# Flippy-tester

A very simple project for creating a standalone "Flippy" service (see
[giftig/flippy](https://www.github.com/giftig/flippy/)) and testing its
interface and an admin interface using docker, nginx, and redis.

Contains a docker-compose file which will bring up the three relevant services,
serving the main flippy interface on port 9001 and the nginx interface on port
9002, the latter of which is configured to allow CORS openly, allowing easy
testing of an interface cross-domain.

Note that you'll need `flippy` cloned into a directory alongside this project,
and the admin interface can be accessed on
http://localhost:8002/static/html/admin.html as it serves `flippy/static` under
the `/static` url, and that's the path to the admin page in that directory.
You can, of course, customise all of this by altering the nginx config or
docker-compose files.
