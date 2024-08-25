# Branching Strategy

## Branches

### `master`

- **Purpose**: 
  - Contains production-ready code.
- **Deployment**:
  - Automatically triggers the CD pipeline for Docker image build and deployment.

### `dev` (or `develop`)
- **Purpose**: 
  - Integrates features and fixes before merging into `master`.
- **Deployment**:
  - Not directly deployed. Used for staging or testing if needed.

### Feature Branches

- **Naming**: `feature/<feature-name>`
- **Purpose**: 
  - For developing new features.
- **Process**:
  - Branch from `dev`, work on the feature, then merge back into `dev`.

### Bugfix Branches

- **Naming**: `bugfix/<bugfix-name>`
- **Purpose**: 
  - For fixing bugs.
- **Process**:
  - Branch from `dev` (or `master` for urgent fixes), apply the fix, then merge back into `dev` and `master`.

### Release Branches

- **Naming**: `release/<release-version>`
- **Purpose**: 
  - For preparing production releases.
- **Process**:
  - Branch from `dev`, finalize the release, then merge into `master` and `dev`.

## Summary

1. **Develop features** in feature branches, merge into `dev`.
2. **Fix bugs** in bugfix branches, merge into `dev` and `master`.
3. **Prepare releases** in release branches, merge into `master` and `dev`.