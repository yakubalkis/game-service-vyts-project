import { Routes, Route } from 'react-router';
import Header from "./components/Header";
import Registration from './pages/auth/Registration';
import Login from './pages/auth/Login';
import UserHomePage from './pages/user/UserHomePage';
import AdminHomePage from './pages/admin/AdminHomePage';
import OAuth2Redirect from "./pages/OAuth2Redirect";


function App() {
  
  return (
    <div>
      <div className="container">
          <Header />

          <Routes>
            <Route path="/" element={<Registration />} />
            <Route path="/login" element={<Login />} />
            <Route path='/oauth2/redirect' element={<OAuth2Redirect />} />
            <Route path="/user/:username" element={<UserHomePage />} />
            <Route path="/admin/:username" element={<AdminHomePage />} />
          </Routes>

      </div>
    </div>
  );
}

export default App;
