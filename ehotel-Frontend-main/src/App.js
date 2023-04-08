import Register from './pages/auth/Register';
import Login from "./features/auth/Login";
import Home from './pages/home/Home';
import Layout from './components/Layout';
import Employee from './features/auth/Employee';
import Missing from './components/Missing';
import Unauthorized from './components/Unauthorized';
import Lounge from './pages/employee/Lounge';
import LinkPage from './components/LinkPage';
import RequireAuth from './features/auth/RequireAuth';
import { Routes, Route } from 'react-router-dom';
import roles from "./roles"


function App() {

  return (
    <Routes>
        <Route path="/" element={<Layout/>}>
          {/* public routes */}
          <Route index element={<Home />}/>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="linkpage" element={<LinkPage />} />
          <Route path="unauthorized" element={<Unauthorized />} />
          <Route path="lounge" element={<Lounge />} />
          {/* protected routes */}
          <Route element={<RequireAuth allowedRoles={[roles.EMPLOYEE]}/>}>
            <Route path="employee" element={<Employee/>} />
          </Route>
        {/* catch all */}
{/*         <Route path="*" element={<Missing />} /> */}
      </Route>
    </Routes>
  );
}

export default App;