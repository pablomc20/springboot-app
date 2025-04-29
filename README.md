# Spring Boot en AWS ECS con RDS y CI/CD (GitHub Actions + OIDC)

Este proyecto demuestra cómo desplegar una aplicación Spring Boot usando:

- Docker + Amazon ECR
- Amazon ECS Fargate
- Load Balancer (ALB)
- Amazon RDS PostgreSQL (Free Tier)
- CI/CD con GitHub Actions y autenticación OIDC

---

## Requisitos

- AWS CLI configurado
- GitHub repository (`lbribiesca-hls/springboot-app`)
- AWS OIDC Role creado (ver `github-oidc-role.yaml`)
- Imagen Docker generada correctamente (con `mvnw` y `Dockerfile` funcional)

---

## 📦 Despliegue de Infraestructura

1. **Subir imagen a ECR (manual):**

2. **Desplegar CloudFormation (ECS + ALB + RDS):**
```bash
./deploy-stack.sh
```

3. **Eliminar recursos al finalizar:**
```bash
./delete-stack.sh
```

---

## 🔐 Despliegue CI/CD con GitHub Actions + OIDC

1. Asegúrate de tener desplegado el OIDC Role:
```bash
aws cloudformation deploy \
  --stack-name github-oidc-role \
  --template-file github-oidc-role-passrole.yaml \
  --capabilities CAPABILITY_NAMED_IAM
```

2. Edita `.github/workflows/deploy.yml` y actualiza:
   - `role-to-assume` con el ARN del rol
   - `ECS_SERVICE` y `TASK_FAMILY` según tu entorno

---

## 🧪 Validación

1. Realiza `git push origin dev`
2. Verifica en GitHub Actions que todos los pasos hayan sido exitosos
3. Accede al dominio del Load Balancer que se imprime como `LoadBalancerDNS` en la salida de CloudFormation

---

## 📁 Estructura esperada

```
.
├── Dockerfile
├── build-ecr.sh
├── deploy-stack.sh
├── delete-stack.sh
├── github-oidc-role.yaml
├── cloudformation.yaml
├── .github
│   └── workflows
│       └── deploy.yml
└── src/
```

---

## 🧹 Limpieza

```bash
./delete-stack.sh
aws cloudformation delete-stack --stack-name github-oidc-role
```

---

*Deploy exitoso con GitHub Actions y OIDC completado.*
