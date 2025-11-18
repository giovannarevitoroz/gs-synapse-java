// =======================================
// SIDEBAR RETRÁTIL
// =======================================

// Captura os elementos
const sidebar = document.querySelector(".sidebar");
const toggleBtn = document.querySelector(".toggle-btn");

// Evento para retrair/expandir
if (toggleBtn) {
    toggleBtn.addEventListener("click", () => {
        sidebar.classList.toggle("collapsed");
    });
}



// =======================================
// TOGGLE DE VISIBILIDADE DA SENHA
// =======================================

const passwordToggles = document.querySelectorAll(".password-toggle");

passwordToggles.forEach(toggle => {
    toggle.addEventListener("click", () => {
        const input = toggle.previousElementSibling;
        const icon = toggle.querySelector("i");

        if (input.type === "password") {
            input.type = "text";
            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");
        } else {
            input.type = "password";
            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");
        }
    });
});



// =======================================
// FORMULÁRIO DE LOGIN
// =======================================

const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const email = document.getElementById("loginEmail").value.trim();
        const senha = document.getElementById("loginPassword").value.trim();

        if (email === "" || senha === "") {
            alert("Preencha todos os campos!");
            return;
        }

        // Aqui você faria uma chamada para o backend
        console.log("Login enviado:", { email, senha });

        alert("Login realizado com sucesso!");
    });
}



// =======================================
// FORMULÁRIO DE REGISTRO
// =======================================

const registerForm = document.getElementById("registerForm");

if (registerForm) {
    registerForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const nome = document.getElementById("registerName").value.trim();
        const email = document.getElementById("registerEmail").value.trim();
        const senha = document.getElementById("registerPassword").value.trim();
        const confirmarSenha = document.getElementById("registerConfirmPassword").value.trim();
        const termos = document.getElementById("acceptTerms").checked;

        if (!nome || !email || !senha || !confirmarSenha) {
            alert("Preencha todos os campos!");
            return;
        }

        if (senha !== confirmarSenha) {
            alert("As senhas não coincidem!");
            return;
        }

        if (!termos) {
            alert("Você precisa aceitar os termos e condições.");
            return;
        }

        // Envio dos dados para backend
        console.log("Registro enviado:", { nome, email, senha });

        alert("Conta criada com sucesso!");
    });
}



// =======================================
// LOG DE CONSISTÊNCIA
// =======================================
console.log("Script carregado com sucesso!");
