# Sample Java Project Deployment - Release Notes

## Overview

This document outlines the release notes and deployment details for the **Sample Java Project**. The application is built from [prabhum10/sample-java-project](https://github.com/prabhum10/sample-java-project.git) and deployed as a containerized service on Azure Container Apps in the North Europe region. The deployment process follows industry best practices for CI/CD, security, and cost optimization.

---

## Objective

- **Maintain control** over the codebase via repository forking and branch management.
- **Automate build, test, and deployment** using CI/CD pipelines.
- **Ensure security** through vulnerability scanning and least privilege access.
- **Monitor and maintain** application health and reliability.
- **Optimize costs** for cloud resources.

---

## Application Build & Deployment Details

### Repository & Branching

- Forked repository: [prabhum10/sample-java-project](https://github.com/prabhum10/sample-java-project.git)
- Branch naming convention:  
  - `main`, `develop`, `feature/<feature-name>`, `bugfix/<issue-id>`

### CI/CD Pipeline

- **CI Pipeline** (e.g., GitHub Actions, Jenkins, GitLab CI):
  - Code checkout
  - Dependency installation (`mvn install`)
  - Static code analysis (SonarQube)
  - Unit testing (`mvn test`)
  - Build artifact creation (`mvn package`)
  - Artifact storage: Secure repository (Nexus/Artifactory)
  - Dependency vulnerability scan (OWASP Dependency-Check)

- **CD Pipeline**:
  - Deploy to staging environment first
  - Use environment variables for secrets/configuration
  - Automated deployment via Infrastructure-as-Code (Terraform/Ansible/Kubernetes manifests)
  - Least privilege for deployment credentials
  - Production deployment after validation

### Build Artifacts

- Location: `/app/builds/0d0d6837`
- Artifacts: `build/libs/*.jar`
- Build ID: `0d0d6837`
- Build status: **Successful**

### Runtime Deployment

- **Azure Container App**:  
  - Name: `swiftphoenix253-app`
  - Resource Group: `ai-mcp-http-rg01`
  - Location: North Europe
  - FQDN: `swiftphoenix253-app--11cr8kt.livelycoast-5d9c2feb.northeurope.azurecontainerapps.io`
  - Status: **Running**
  - Managed Environment: `swiftphoenix253-env`
  - Outbound IP: `52.158.119.181`

---

## Security Scan Summary

- **Scan Tool**: Automated (vuln, misconfig, secret, license)
- **Target**: [prabhum10/sample-java-project](https://github.com/prabhum10/sample-java-project)
- **Severities Scanned**: CRITICAL, HIGH, MEDIUM, LOW, UNKNOWN
- **Dockerfile Findings**: 2 (severity breakdown available in scan report)
- **Recommendations**:
  - Address all critical/high findings before production deployment.
  - Regularly update dependencies and base images.
  - Enable logging and alerting for security and reliability.

---

## Cost Estimate

| Resource            | SKU/Type | Monthly Cost (USD) |
|---------------------|----------|--------------------|
| Web App             | B1       | $9.71              |
| App Service Plan    | B1       | $9.71              |
| Storage Account     | LRS, 100GB | $2.08            |
| Container Apps      | 10,000 runs | $5.00            |
| **Total**           |          | **$26.50**         |

> **Note:** Prices sourced from Azure Retail Prices API. Fallback to static rates if unavailable.

---

## High-Level Network Diagram

```mermaid
flowchart TD
    User[User]
    Internet[Internet]
    AzureApp[Azure Container App<br>swiftphoenix253-app]
    ManagedEnv[Managed Environment<br>swiftphoenix253-env]
    ArtifactRepo[Artifact Repository<br>(Nexus/Artifactory)]
    Storage[Azure Storage Account]
    Monitoring[Monitoring & Logging<br>(Prometheus/Grafana/ELK)]

    User --> Internet
    Internet --> AzureApp
    AzureApp --> ManagedEnv
    AzureApp --> Storage
    AzureApp --> Monitoring
    AzureApp --> ArtifactRepo
```

---

## Monitoring & Maintenance

- **Monitoring**: Prometheus, Grafana, ELK stack
- **Logging & Alerting**: Enabled for security and reliability
- **Documentation**: Pipeline and deployment process documented for maintainability

---

## References

- [Sample Java Project Repository](https://github.com/prabhum10/sample-java-project.git)
- [Azure Container Apps Documentation](https://learn.microsoft.com/en-us/azure/container-apps/)
- [OWASP Dependency-Check](https://owasp.org/www-project-dependency-check/)
- [SonarQube](https://www.sonarqube.org/)

---

**For further details, refer to internal documentation or contact the DevOps team.**