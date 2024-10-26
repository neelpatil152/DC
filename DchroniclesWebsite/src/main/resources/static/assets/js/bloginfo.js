


    /* JavaScript for handling the popup
    const popup = document.getElementById("popup");
    const popupInfo = document.getElementById("popup-info");
    const closeBtn = document.querySelector(".close");
    const knowMoreButtons = document.querySelectorAll(".knowMore");

    knowMoreButtons.forEach(button => {
        button.addEventListener("click", function() {
            const info = this.getAttribute("data-info");
            popupInfo.textContent = info;
            popup.style.display = "block";
        });
    });

    closeBtn.onclick = function() {
        popup.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == popup) {
            popup.style.display = "none";
        }
    }*/


        /*
    function toggleInfo() {
        var extraInfo = document.getElementById("extraInfo");
        if (extraInfo.style.display === "none" || extraInfo.style.display === "") {
          extraInfo.style.display = "block";
        } else {
          extraInfo.style.display = "none";
        }
      }
*/




     /* function toggleInfo(element) {
        var extraInfo = element.nextElementSibling;
        if (extraInfo.style.display === "none" || extraInfo.style.display === "") {
          extraInfo.style.display = "block";
          element.textContent = "Show less";
        } else {
          extraInfo.style.display = "none";
          element.textContent = "Know more";
        }
      }*/




        document.addEventListener('DOMContentLoaded', function() {
            // Open modal functionality
            document.querySelectorAll('.open-modal-btn').forEach(button => {
              button.addEventListener('click', function() {
                const modalClass = this.getAttribute('data-modal');
                const modal = document.querySelector(`.${modalClass}`);
                if (modal) {
                  modal.style.display = 'block';
                }
              });
            });
          
            // Close modal functionality
            document.querySelectorAll('.close-btn').forEach(span => {
              span.addEventListener('click', function() {
                const modal = this.closest('.modal');
                if (modal) {
                  modal.style.display = 'none';
                }
              });
            });
          
            // Close modal when clicking outside of the modal
            window.addEventListener('click', function(event) {
              if (event.target.classList.contains('modal')) {
                event.target.style.display = 'none';
              }
            });
          });