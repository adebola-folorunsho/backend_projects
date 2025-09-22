from fpdf import FPDF

pdf = FPDF()
pdf.add_page()
pdf.set_auto_page_break(auto=True, margin=15)
pdf.set_font("Arial", size=12)

content = """
🐳 Install Docker Desktop 4.22 and Run MySQL on macOS Monterey (Intel)

1. Download Docker Desktop 4.22 (compatible with macOS Monterey 12.7.4, Intel)
- Download Docker Desktop 4.22.0 for Intel Macs:
  https://desktop.docker.com/mac/main/amd64/117440/Docker.dmg

2. Install Docker Desktop
- Open the downloaded .dmg file.
- Drag the Docker icon into Applications.
- Launch Docker from Applications.
- Allow system permissions if prompted.
- Wait for Docker whale icon in menu bar.
- Skip sign-in if prompted.

3. Verify Docker Installation
Run in Terminal:
  docker --version
  docker compose version
  docker run hello-world

4. Create a Project Folder
In Terminal:
  mkdir mysql-docker
  cd mysql-docker

5. Create docker-compose.yml
Run:
  touch docker-compose.yml
  open -a TextEdit docker-compose.yml
Paste:

version: '3.9'

services:
  mysql:
    image: mysql:8.4
    container_name: my-mysql
    restart: unless-stopped
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: my-secret-pw
      MYSQL_DATABASE: mydb
      MYSQL_USER: myuser
      MYSQL_PASSWORD: mypass
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:

Save and close the file.

6. Start MySQL Container
Run:
  docker compose up -d

7. Verify MySQL Running
Run:
  docker ps
You should see 'my-mysql' running on port 3306.

8. Connect to MySQL via Terminal
Run:
  docker exec -it my-mysql mysql -uroot -p
Enter password: my-secret-pw
Then run:
  SHOW DATABASES;

9. (Optional) Connect with GUI
Host: 127.0.0.1
Port: 3306
User: myuser
Password: mypass
Database: mydb

10. Useful Commands
  docker compose up -d           # Start container
  docker compose down            # Stop container
  docker compose down -v         # Stop and remove volumes (data loss)
  docker compose logs -f         # View logs
  docker exec -it my-mysql bash # Enter container shell

🎉 You’re done!
"""

for line in content.split('\n'):
    pdf.cell(0, 8, line, ln=True)

pdf.output("Docker_MySQL_macOS_Monterey_Guide.pdf")
print("PDF generated successfully!")
