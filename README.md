# parrot
Parrot is a small HTTP Server used in my Kubernetes/OpenShift/DevOps Workshops 

## Building Docker Images

### Using the Makefile

Build all Docker image variants (blue, green, latest, and versioned):

```bash
make docker
```

This creates the following images:
- `docker.io/tinmancoding/parrot:blue` - Blue variant
- `docker.io/tinmancoding/parrot:green` - Green variant
- `docker.io/tinmancoding/parrot:latest` - Latest version
- `docker.io/tinmancoding/parrot:v<VERSION>` - Version-tagged image (e.g., `v0.0.1-SNAPSHOT`, extracted from pom.xml)

### Building Manually

Build a single Docker image:

```bash
docker build -t parrot .
```

Build with a specific color:

```bash
docker build --build-arg PARROT_COLOR=blue -t parrot:blue .
docker build --build-arg PARROT_COLOR=green -t parrot:green .
```

## Running Locally

Run the container:

```bash
docker run -d -p 8080:8080 --name parrot parrot
```

Run with a specific color environment variable:

```bash
docker run -d -p 8080:8080 -e PARROT_COLOR=red --name parrot parrot
```

## Testing with curl

Once the container is running, you can test it with curl:

```bash
curl http://localhost:8080/
```

Example response:

```json
{
  "hostname": "abc123def456",
  "timestamp": "2024-01-15T10:30:00Z",
  "uptime": 42,
  "color": "blue",
  "env": {
    "PARROT_COLOR": "blue",
    "PATH": "/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
  }
}
```

### Stopping the Container

```bash
docker stop parrot
docker rm parrot
```
