<script>
import NavbarLink from "./NavbarLink.vue";

export default {
  components: { NavbarLink },
  setup() {},
};
</script>

<template>
  <nav
    class="navbar fixed-top navbar-expand-lg navbar-light"
  >
    <div class="container-fluid">
      <NavbarLink
        class="navbar-brand"
        link="/"
        displayName="Linked Care"
      ></NavbarLink>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <NavbarLink
              class="nav-link"
              link="/patients"
              displayName="Patienten"
            ></NavbarLink>
          </li>
          <li class="nav-item">
            <NavbarLink
              class="nav-link"
              link="/carePlans"
              displayName="Care Plans"
            ></NavbarLink>
          </li>
          <li class="nav-item">
            <NavbarLink
              class="nav-link"
              link="/about"
              displayName="About"
            ></NavbarLink>
          </li>
          <form class="d-flex">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Suche Patienten"
              aria-label="Search"
            />
            <button class="btn btn-secondary me-2" type="submit">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-search"
                viewBox="0 0 16 16"
              >
                <path
                  d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                />
              </svg>
            </button>
          </form>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <NavbarLink
                  class="nav-link"
                  link="/profile"
                  displayName="User123"
                ></NavbarLink>
          </li>
          <li class="nav-item dropdown dropstart">
            <img
              class="navProfilePic dropdown-toggle"
              src="../../assets/genericProfile.png"
              alt="../../assets/genericProfile.png"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            />
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <NavbarLink
                  link="/profile"
                  displayName="Profil bearbeiten"
                  class="dropdown-item"
                ></NavbarLink>
              </li>
              <li>
                <NavbarLink
                  link="/settings"
                  displayName="Einstellungen"
                  class="dropdown-item"
                ></NavbarLink>
              </li>
              <li>
                <NavbarLink
                  link="/login"
                  displayName="Logout"
                  class="dropdown-item"
                ></NavbarLink>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navProfilePic {
  border: 1px solid black;
  height: 50px;
  width: 50px;
  border-radius: 50px;
  box-shadow: 0px 0px 3px rgb(88, 87, 87);
  float: right;
}

.navProfilePic:hover {
  cursor: pointer;
}

nav {
  background-color: #e3f2fd;
}
</style>
