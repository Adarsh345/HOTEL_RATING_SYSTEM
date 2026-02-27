# Cloud Deployment Starter Pack

This folder gives you two deployment paths for the HOTEL_RATING_SYSTEM:

1. **Docker Compose** for quick public deployment on a VM.
2. **Kubernetes** for scalable cloud deployment.

## 1) Build JARs first
Run these from the repo root:

```bash
cd ServiceRegistry/ServiceRegistry && ./mvnw clean package -DskipTests
cd ../../ConfigServer/ConfigServer && ./mvnw clean package -DskipTests
cd ../../HotelService/HotelService && ./mvnw clean package -DskipTests
cd ../../RatingService/RatingService && ./mvnw clean package -DskipTests
cd ../../UserService/UserService && ./mvnw clean package -DskipTests
cd ../../ApiGateway/ApiGateway && ./mvnw clean package -DskipTests
cd ../../
```

## 2) Docker Compose (single VM)

```bash
cd deploy
docker compose up --build -d
```

Public endpoint after deployment:
- `http://<your-vm-ip>:8085/users/...`
- `http://<your-vm-ip>:8085/hotel/...`
- `http://<your-vm-ip>:8085/ratings/...`

### Notes
- For production, put Nginx/Caddy in front of `8085` and enable HTTPS.
- Replace local Mongo with a managed MongoDB URI by setting `SPRING_DATA_MONGODB_URI` env vars.

## 3) Kubernetes deployment

### Prerequisites
- A Kubernetes cluster (EKS/GKE/AKS or self-hosted).
- Your Docker images pushed to a registry (`hotel-rating/*` image names in manifests).

### Apply manifests

```bash
kubectl apply -f deploy/k8s/00-namespace.yaml
kubectl apply -f deploy/k8s/01-mongo.yaml
kubectl apply -f deploy/k8s/02-service-registry.yaml
kubectl apply -f deploy/k8s/03-config-server.yaml
kubectl apply -f deploy/k8s/04-domain-services.yaml
kubectl apply -f deploy/k8s/05-api-gateway.yaml
```

### Verify

```bash
kubectl get pods -n hotel-rating
kubectl get svc -n hotel-rating
```

Get gateway public IP:

```bash
kubectl get svc api-gateway -n hotel-rating
```

Then test:

```bash
curl http://<gateway-external-ip>/users/
curl http://<gateway-external-ip>/hotel/
curl http://<gateway-external-ip>/ratings
```

## Recommended next hardening
- Add health/readiness probes.
- Add TLS + domain with Ingress.
- Move secrets to Kubernetes Secrets / cloud secret manager.
- Use managed MongoDB (Atlas or cloud provider) instead of in-cluster Mongo.
