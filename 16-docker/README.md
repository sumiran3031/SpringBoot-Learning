# Day 16 — Docker + Containerization

## What is Docker?
Docker packages your app into a container with everything it needs
(Java, dependencies, config) — runs the same on any machine.

## Key Concepts
| Term | Description |
|------|-------------|
| Image | Blueprint/recipe for a container |
| Container | Running instance of an image |
| Dockerfile | Instructions to build an image |
| docker-compose | Manage multiple containers |

## Single Container (Spring Boot + H2)
```bash
# Build image
docker build -t springboot-app .

# Run container
docker run -p 8080:8080 springboot-app
```

## Multi Container (Spring Boot + MySQL)
```bash
# Start all containers
docker-compose up

# Stop all containers
docker-compose down
```

## Useful Commands
```bash
docker ps                    # running containers
docker images                # all images
docker stop <container-id>   # stop container
docker rm <container-id>     # delete container
docker rmi <image-name>      # delete image
docker logs <container-id>   # view logs
```

## Tested On
- ✅ 05-jpa-h2-database → single container
- ✅ 08-mysql-database  → multi container (docker-compose)
