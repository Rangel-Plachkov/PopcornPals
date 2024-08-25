# CD Pipeline Workflow

## Overview

The CD workflow is triggered by the completion of the "CI" workflow on the `master` branch. It automates the process of building a Docker image and deploying it to a Minikube Kubernetes cluster.

### Environment Variables

- **`SERVER_PATH`**: The path to the server code (`PopcornPals-Server`).
- **`DOCKEHUB_URL`**: The Docker Hub repository URL for the Docker image (`rangelplachkov/popcorn-pals-server:latest`).

## Jobs

### 1. Docker-Deployment

This job builds and pushes the Docker image to Docker Hub.

- **Runs-on**: `ubuntu-latest`
- **Steps**:
  - **Checkout repository**: Uses `actions/checkout@v4` to clone the repository.
  - **Download Artifact**: Retrieves build artifacts from the CI workflow.
  - **Log in to Docker Hub**: Authenticates with Docker Hub using credentials stored in secrets.
  - **Check Docker Hub login status**: Verifies Docker Hub login status.
  - **Build and push Docker image**: Builds the Docker image using the Dockerfile and pushes it to Docker Hub.

### 2. Kubernetes-Deployment

This job deploys the Docker image to a Minikube Kubernetes cluster.

- **Runs-on**: `ubuntu-latest`
- **Needs**: `Docker-Deployment`
- **Steps**:
  - **Setup Minikube**: Installs and configures Minikube.
  - **Checkout**: Uses `actions/checkout@v4` to clone the repository.
  - **Pull Docker image**: Pulls the Docker image from Docker Hub.
  - **Minikube rollout**: Applies Kubernetes configurations and monitors the deployment status.
  - **Check Kubernetes status**: Retrieves the status of all Kubernetes resources.
  - **Stop Minikube**: Stops the Minikube cluster.
