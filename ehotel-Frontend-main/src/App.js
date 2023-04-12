import Register from './components/Form/RegisterForm';
import Login from "./components/Form/LoginForm";
import Home from './pages/home/Home';
import Layout from './components/Layout';
import Employee from './features/auth/Employee';
import Missing from './pages/auth/Missing';
import Unauthorized from './pages/auth/Unauthorized';
import Lounge from './pages/employee/Lounge';
import LinkPage from './pages/LinkPage';
import RequireAuth from './features/auth/RequireAuth';
import { Routes, Route } from 'react-router-dom';
import roles from "./roles"
import styles from './App.css'
import SearchPage from './pages/search/SearchPage';
import RoomPage from './pages/hotel/RoomPage';


function App() {

  return (
    <Routes>
        <Route path="/" element={<Layout/>}>
          {/* public routes */}
          <Route index element={<Home />}/>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="linkpage" element={<LinkPage />} />
          <Route path="search" element={<SearchPage />} />
          <Route path='room' element={<RoomPage/>}/>
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