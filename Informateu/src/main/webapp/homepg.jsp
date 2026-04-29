<%@ page import="java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - InformateU</title>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap');

    :root {
      --primary-color: #4F46E5;
      --primary-hover: #4338CA;
      --bg-color: #EEF2FF;
      --text-main: #111827;
      --text-muted: #6B7280;
      --border-color: #E5E7EB;
      --card-bg: #FFFFFF;
    }

    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Inter', sans-serif;
    }

    body {
      background-color: var(--bg-color);
      color: var(--text-main);
      display: flex;
      min-height: 100vh;
    }

    /* Sidebar */
    .sidebar {
      width: 280px;
      background-color: var(--card-bg);
      border-right: 1px solid var(--border-color);
      display: flex;
      flex-direction: column;
      padding: 24px;
    }

    .brand-header {
      display: flex;
      align-items: center;
      margin-bottom: 40px;
    }

    .brand-icon {
      width: 36px;
      height: 36px;
      background-color: var(--primary-color);
      border-radius: 8px;
      display: inline-flex;
      justify-content: center;
      align-items: center;
      margin-right: 12px;
      color: white;
    }

    .brand-icon svg {
      width: 20px;
      height: 20px;
      fill: currentColor;
    }

    .brand-name {
      font-size: 1.5rem;
      font-weight: 700;
      color: var(--text-main);
    }

    .nav-menu {
      display: flex;
      flex-direction: column;
      gap: 12px;
    }

    .nav-item {
      display: flex;
      align-items: center;
      padding: 12px 16px;
      border-radius: 8px;
      color: var(--text-main);
      text-decoration: none;
      font-weight: 600;
      transition: background-color 0.2s;
    }

    .nav-item:hover,
    .nav-item.active {
      background-color: var(--bg-color);
      color: var(--primary-color);
    }

    .nav-item svg {
      width: 20px;
      height: 20px;
      margin-right: 12px;
    }

    /* Main Content */
    .main-content {
      flex: 1;
      padding: 40px;
      overflow-y: auto;
    }

    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 32px;
    }

    .header h1 {
      font-size: 2rem;
      font-weight: 800;
      letter-spacing: -0.5px;
    }

    .user-profile {
      display: flex;
      align-items: center;
      gap: 12px;
      font-weight: 600;
    }

    .avatar {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      background-color: var(--primary-color);
      color: white;
      display: flex;
      align-items: center;
      justify-content: center;
      font-weight: bold;
    }

    /* Content Cards */
    .dashboard-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
      gap: 24px;
    }

    .card {
      background-color: var(--card-bg);
      border: 1px solid var(--border-color);
      border-radius: 12px;
      padding: 24px;
      box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
      transition: transform 0.2s, box-shadow 0.2s;
    }

    .card:hover {
      transform: translateY(-4px);
      box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
    }

    .card-tag {
      display: inline-block;
      padding: 4px 10px;
      background-color: var(--bg-color);
      color: var(--primary-color);
      border-radius: 20px;
      font-size: 0.75rem;
      font-weight: 700;
      margin-bottom: 12px;
    }

    .card-title {
      font-size: 1.25rem;
      font-weight: 700;
      margin-bottom: 8px;
    }

    .card-desc {
      color: var(--text-muted);
      font-size: 0.95rem;
      line-height: 1.5;
      margin-bottom: 20px;
    }

    .card-footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-top: 1px solid var(--border-color);
      padding-top: 16px;
      font-size: 0.85rem;
      color: var(--text-muted);
    }

    .btn-primary {
      background-color: var(--text-main);
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 8px;
      font-weight: 600;
      cursor: pointer;
      transition: background-color 0.2s;
    }

    .btn-primary:hover {
      background-color: #1F2937;
    }

    .select-domain {
      padding: 10px 16px;
      border: 1px solid var(--border-color);
      border-radius: 8px;
      background-color: var(--card-bg);
      color: var(--text-main);
      font-weight: 600;
      font-size: 0.9rem;
      cursor: pointer;
      outline: none;
      transition: all 0.2s;
      margin-right: 12px;
      appearance: none;
      background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 24 24' stroke='%236B7280'%3E%3Cpath stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M19 9l-7 7-7-7'%3E%3C/path%3E%3C/svg%3E");
      background-repeat: no-repeat;
      background-position: right 12px center;
      background-size: 16px;
      padding-right: 36px;
    }

    .select-domain:focus {
      border-color: var(--primary-color);
      box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
    }

    .welcome-banner {
      background: linear-gradient(135deg, var(--primary-color), var(--primary-hover));
      border-radius: 16px;
      padding: 40px;
      color: white;
      margin-bottom: 32px;
      position: relative;
      overflow: hidden;
    }

    .welcome-banner h2 {
      font-size: 2.2rem;
      font-weight: 800;
      margin-bottom: 10px;
    }

    .welcome-banner p {
      font-size: 1.1rem;
      opacity: 0.9;
      max-width: 600px;
    }

    .banner-decoration {
      position: absolute;
      right: 40px;
      bottom: -40px;
      font-size: 10rem;
      font-weight: bold;
      font-style: italic;
      color: rgba(255, 255, 255, 0.1);
      pointer-events: none;
      line-height: 1;
    }

    @media (max-width: 900px) {
      body {
        flex-direction: column;
      }

      .sidebar {
        width: 100%;
        border-right: none;
        border-bottom: 1px solid var(--border-color);
        padding: 16px;
      }

      .nav-menu {
        flex-direction: row;
        overflow-x: auto;
      }

      .nav-item {
        white-space: nowrap;
      }

      .main-content {
        padding: 20px;
      }
    }
  </style>
</head>

<body>

  <!-- SIDEBAR -->
  <div class="sidebar">
    <div class="brand-header">
      <div class="brand-icon">
        <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path d="M12 2L2 7L12 12L22 7L12 2Z" />
          <path d="M2 12L12 17L22 12" />
          <path d="M2 17L12 22L22 17" />
        </svg>
      </div>
      <div class="brand-name">InformateU</div>
    </div>

    <div class="nav-menu">
      <a href="#" class="nav-item active">
        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
            d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6">
          </path>
        </svg>
        Home
      </a>
      <a href="Displayinfo" class="nav-item">
        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
            d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z">
          </path>
        </svg>
        My Ideas
      </a>
      <a href="login.html" class="nav-item" style="margin-top: auto;">
        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
            d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
        </svg>
        Log Out
      </a>
    </div>
  </div>

  <!-- MAIN CONTENT -->
  <div class="main-content">
    <div class="header">
      <h1>Explore Ideas</h1>
      <div class="user-profile">
        <select class="select-domain" id="domainFilter">
          <option value="all">All Domains</option>
          <option value="technology">Technology</option>
          <option value="mathematics">Mathematics</option>
          <option value="science">Science</option>
          <option value="design">Design</option>
          <option value="productivity">Productivity</option>
          <option value="business">Business</option>
        </select>
        <form action="info.html" method="POST">
          <button class="btn-primary" style="margin-right: 16px;">+ Share Idea</button>
        </form>

        <div class="avatar">U</div>
        <span></span>
      </div>
    </div>

    <div class="welcome-banner">
      <div class="banner-decoration">Σ</div>
      <h2>Welcome back to InformateU!</h2>
      <p>Your brain is a shared resource. Discover what others are learning, share your latest insights, and collaborate
        with the community today.</p>
    </div>
    <div class="dashboard-grid">

<%
List<Map<String,String>> data = (List<Map<String,String>>) request.getAttribute("data");

if(data != null && !data.isEmpty()){
    for(Map<String,String> row : data){
%>

<div class="card">
  <span class="card-tag">
    <%= row.get("domain") %>
  </span>

  <h3 class="card-title">
    <%= row.get("info") %>
  </h3>

  <p class="card-desc">
    <%= row.get("info") %>
  </p>

  <div class="card-footer">
    <span>By <strong>User</strong></span>
  </div>
</div>

<%
    }
} else {
%>

<p>No ideas available</p>

<%
}
%>

</div>

    
  </div>

  <script>
    document.getElementById('domainFilter').addEventListener('change', function() {
      const selectedValue = this.value.toLowerCase();
      const cards = document.querySelectorAll('.card');

      cards.forEach(card => {
        const tag = card.querySelector('.card-tag').textContent.toLowerCase();
        if (selectedValue === 'all' || tag.includes(selectedValue)) {
          card.style.display = 'block';
        } else {
          card.style.display = 'none';
        }
      });
    });
  </script>
</body>

</html>