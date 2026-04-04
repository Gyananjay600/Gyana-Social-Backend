@echo off
REM Git Repository Setup Script for Gyana Social Backend
REM This script initializes git and prepares for pushing to GitHub

echo.
echo ========================================
echo Gyana Social Backend - Git Setup Script
echo ========================================
echo.

REM Check if git is installed
git --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Git is not installed or not in PATH
    echo Please install Git from https://git-scm.com/download/win
    exit /b 1
)

echo [1/5] Checking for existing git repository...
if exist .git (
    echo Git repository already exists.
    echo Skipping initialization...
) else (
    echo [1/5] Initializing git repository...
    git init
    if errorlevel 1 (
        echo ERROR: Failed to initialize git repository
        exit /b 1
    )
    echo ✓ Git repository initialized
)

echo.
echo [2/5] Configuring git user...
echo.
echo Enter your Git username (e.g., yourusername):
set /p GIT_USER=
if "%GIT_USER%"=="" (
    echo ERROR: Username cannot be empty
    exit /b 1
)

echo Enter your Git email (e.g., your@email.com):
set /p GIT_EMAIL=
if "%GIT_EMAIL%"=="" (
    echo ERROR: Email cannot be empty
    exit /b 1
)

git config user.name "%GIT_USER%"
git config user.email "%GIT_EMAIL%"
git config --local user.name "%GIT_USER%"
git config --local user.email "%GIT_EMAIL%"
echo ✓ Git user configured: %GIT_USER% (%GIT_EMAIL%)

echo.
echo [3/5] Adding files to git...
git add .
if errorlevel 1 (
    echo ERROR: Failed to add files
    exit /b 1
)
echo ✓ Files added to staging area

echo.
echo [4/5] Creating initial commit...
git commit -m "Initial commit: Gyana Social Backend v1.0.0

- Comprehensive Spring Boot social media platform
- User management with JWT authentication
- Posts, comments, reels, and real-time messaging
- Security fixes and best practices applied
- Complete API documentation
- Docker and Kubernetes ready
- Production-grade code quality"

if errorlevel 1 (
    echo WARNING: Nothing to commit (repository might already have commits)
) else (
    echo ✓ Initial commit created
)

echo.
echo [5/5] Repository Setup Complete!
echo.
echo ========================================
echo Next Steps:
echo ========================================
echo.
echo 1. Create a new repository on GitHub:
echo    - Go to https://github.com/new
echo    - Repository name: Gyana-Social-Backend
echo    - Description: A modern social media platform backend API built with Spring Boot
echo    - Choose: Public (recommended for open source)
echo    - DO NOT initialize with README, .gitignore, or license
echo.
echo 2. Add remote repository:
echo    git remote add origin https://github.com/YOUR_USERNAME/Gyana-Social-Backend.git
echo.
echo 3. Verify remote:
echo    git remote -v
echo.
echo 4. Push to GitHub:
echo    git branch -M main
echo    git push -u origin main
echo.
echo 5. (Optional) Create additional branches:
echo    git checkout -b develop
echo    git push -u origin develop
echo.
echo ========================================
echo Repository Information:
echo ========================================
echo Repository Status:
git status --short
echo.
echo Commits:
git log --oneline -5
echo.
echo Current Branch:
git branch -v
echo.
echo Remotes:
git remote -v
echo.
echo ========================================
echo Documentation Files Created:
echo ========================================
echo - README.md              : Main documentation
echo - QUICKSTART.md          : Get started in 5 minutes
echo - DEPLOYMENT.md          : Deployment guide
echo - CONTRIBUTING.md        : Contributing guidelines
echo - CHANGELOG.md           : Version history
echo - FIXES_APPLIED.md       : Security fixes documentation
echo - LICENSE                : MIT License
echo - Dockerfile             : Docker container configuration
echo - docker-compose.yml     : Docker Compose setup
echo - .github/workflows/     : GitHub Actions CI/CD
echo - .env.example           : Environment variables template
echo.
echo ========================================
pause
