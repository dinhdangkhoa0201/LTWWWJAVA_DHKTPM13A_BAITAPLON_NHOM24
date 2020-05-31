// admin/index.html
function openNav() {
  document.getElementById('btn-menunav').onclick = closeNav;
  document.getElementById('left-sidebar').style.width = '300px';
  document.getElementById('page-content').style.marginLeft = '300px';
}

function closeNav() {
  document.getElementById('btn-menunav').onclick = openNav;
  document.getElementById('left-sidebar').style.width = '0px';
  document.getElementById('page-content').style.marginLeft = '0px';
}

var dropdown = document.getElementById('dropdownMangeMenu');
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener('click', function () {
    this.classList.toggle('active');
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === 'block') {
      dropdownContent.style.display = 'none';
    } else {
      dropdownContent.style.display = 'block';
    }
  });
}
