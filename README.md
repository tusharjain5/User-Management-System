<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
</head>
<body>
  <h1 style="text-align: center;">User Management System</h1>

  <h2>Overview</h2>
  <p>
    The <strong>User Management System</strong> is a web application designed to manage users efficiently by assigning them roles. It enables:
  </p>
  <ul>
    <li>User registration with email verification.</li>
    <li>Role-based access for <strong>Admin</strong>, <strong>Teacher</strong>, and <strong>User (Student)</strong>.</li>
    <li>Admin to decide and assign roles to registered users.</li>
  </ul>

  <h2>Features</h2>
  <ol>
    <li><strong>User Registration:</strong>
      <ul>
        <li>Users can register with their email addresses.</li>
        <li>A verification link is sent to their email to activate the account.</li>
      </ul>
    </li>
    <li><strong>Role Management:</strong>
      <ul>
        <li>Roles are predefined as <em>Admin</em>, <em>Teacher</em>, and <em>User (Student)</em>.</li>
        <li>Admin has the authority to assign roles.</li>
      </ul>
    </li>
    <li><strong>Secure Login:</strong>
      <ul>
        <li>User authentication and role-based access.</li>
      </ul>
    </li>
  </ol>

  <h2>Technologies Used</h2>
  <ul>
    <li><strong>Backend:</strong> Spring Boot, Hibernate, Lombok, Java</li>
    <li><strong>Frontend:</strong> HTML5, CSS, Bootstrap 5, Thymeleaf</li>
    <li><strong>Database:</strong> SQL</li>
    <li><strong>Core Java Concepts</strong></li>
  </ul>

  <h2>How It Works</h2>
  <ol>
    <li>A user registers on the platform.</li>
    <li>A verification email with a link is sent to their Gmail ID.</li>
    <li>Upon email verification, the user's account is activated.</li>
    <li>Admin can log in and assign roles such as <strong>Teacher</strong> or <strong>User (Student)</strong> to the newly registered users.</li>
  </ol>

  <h2>Setup Instructions</h2>
  <ol>
    <li>Clone this repository:
      <pre><code>git clone &lt;repository-link&gt;</code></pre>
    </li>
    <li>Navigate to the project directory:
      <pre><code>cd user-management-system</code></pre>
    </li>
    <li>Configure the database connection in <code>application.properties</code>.</li>
    <li>Build and run the application:
      <pre><code>mvn spring-boot:run</code></pre>
    </li>
    <li>Access the application at <code>http://localhost:8080</code>.</li>
  </ol>

  <h2>Future Enhancements</h2>
  <ul>
    <li>Add password recovery functionality.</li>
    <li>Introduce role-specific dashboards.</li>
 
  </ul>

  <h2>License</h2>
  <p>This project is licensed under the MIT License.</p>
</body>
</html>
