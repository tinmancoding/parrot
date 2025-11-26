# Docker image configuration
DOCKER_REGISTRY := docker.io
DOCKER_REPO := tinmancoding/parrot
PLATFORM := linux/amd64

# Extract version from pom.xml (project version, not parent version)
VERSION := $(shell awk '/<\/parent>/,/<version>/' pom.xml | grep '<version>' | head -1 | sed 's/.*<version>\(.*\)<\/version>.*/\1/')

# Image tags
IMAGE_BLUE := $(DOCKER_REGISTRY)/$(DOCKER_REPO):blue
IMAGE_GREEN := $(DOCKER_REGISTRY)/$(DOCKER_REPO):green
IMAGE_LATEST := $(DOCKER_REGISTRY)/$(DOCKER_REPO):latest
IMAGE_VERSION := $(DOCKER_REGISTRY)/$(DOCKER_REPO):v$(VERSION)

.PHONY: docker docker-push

docker:
	docker build --platform $(PLATFORM) --build-arg PARROT_COLOR=blue -t $(IMAGE_BLUE) .
	docker build --platform $(PLATFORM) --build-arg PARROT_COLOR=green -t $(IMAGE_GREEN) .
	docker build --platform $(PLATFORM) -t $(IMAGE_LATEST) .
	docker build --platform $(PLATFORM) -t $(IMAGE_VERSION) .

docker-push:
	docker push $(IMAGE_BLUE)
	docker push $(IMAGE_GREEN)
	docker push $(IMAGE_LATEST)
	docker push $(IMAGE_VERSION)
