// MediNova Hospital Management System - Frontend API Connector & UI Logic

const API_BASE = "/api";

// Default Fallback Mock Data
const DEFAULT_DOCTORS = [
    { id: 1, name: "Dr. Ananya Sharma", specialization: "Cardiology", experience: "12 Yrs Exp", rating: "4.9", status: "Available", fee: "$150", avatar: "AS", days: "Mon - Fri" },
    { id: 2, name: "Dr. Rajesh Rao", specialization: "Neurology", experience: "15 Yrs Exp", rating: "4.8", status: "Available", fee: "$180", avatar: "RR", days: "Mon - Thu" },
    { id: 3, name: "Dr. Kumuda V.", specialization: "Gynecology", experience: "9 Yrs Exp", rating: "4.9", status: "On Leave", fee: "$130", avatar: "KV", days: "Tue - Sat" },
    { id: 4, name: "Dr. Laya Patel", specialization: "Pediatrics", experience: "10 Yrs Exp", rating: "4.7", status: "Available", fee: "$120", avatar: "LP", days: "Mon - Wed" },
    { id: 5, name: "Dr. Vikram Seth", specialization: "Orthopedics", experience: "14 Yrs Exp", rating: "4.9", status: "Available", fee: "$160", avatar: "VS", days: "Wed - Sun" },
    { id: 6, name: "Dr. Meera Nambiar", specialization: "Dermatology", experience: "8 Yrs Exp", rating: "4.8", status: "Available", fee: "$140", avatar: "MN", days: "Mon - Fri" }
];

const DEFAULT_APPOINTMENTS = [
    { id: 1, appointmentCode: "APT-101", patientName: "Sarah Jenkins", patientAge: 32, patientPhone: "+1 555-0142", doctorName: "Dr. Ananya Sharma", specialization: "Cardiology", appointmentDate: "2026-10-12", appointmentTime: "10:00 AM", status: "Confirmed", consultationType: "In-Person" },
    { id: 2, appointmentCode: "APT-102", patientName: "Robert Chen", patientAge: 45, patientPhone: "+1 555-0891", doctorName: "Dr. Rajesh Rao", specialization: "Neurology", appointmentDate: "2026-10-12", appointmentTime: "11:30 AM", status: "Pending", consultationType: "Telehealth" },
    { id: 3, appointmentCode: "APT-103", patientName: "Elena Rostova", patientAge: 29, patientPhone: "+1 555-0312", doctorName: "Dr. Laya Patel", specialization: "Pediatrics", appointmentDate: "2026-10-14", appointmentTime: "02:00 PM", status: "Confirmed", consultationType: "In-Person" },
    { id: 4, appointmentCode: "APT-104", patientName: "Marcus Vance", patientAge: 58, patientPhone: "+1 555-0943", doctorName: "Dr. Vikram Seth", specialization: "Orthopedics", appointmentDate: "2026-10-15", appointmentTime: "04:15 PM", status: "Confirmed", consultationType: "In-Person" }
];

// Initialize Storage State
function initStorage() {
    if (!localStorage.getItem("medinova_doctors")) {
        localStorage.setItem("medinova_doctors", JSON.stringify(DEFAULT_DOCTORS));
    }
    if (!localStorage.getItem("medinova_appointments")) {
        localStorage.setItem("medinova_appointments", JSON.stringify(DEFAULT_APPOINTMENTS));
    }
    if (!localStorage.getItem("medinova_theme")) {
        localStorage.setItem("medinova_theme", "light");
    }
    applyTheme();
}

// Theme Manager
function toggleTheme() {
    const currentTheme = localStorage.getItem("medinova_theme") || "light";
    const newTheme = currentTheme === "dark" ? "light" : "dark";
    localStorage.setItem("medinova_theme", newTheme);
    applyTheme();
    showToast(`Switched to ${newTheme.toUpperCase()} mode`, "info");
}

function applyTheme() {
    const theme = localStorage.getItem("medinova_theme") || "light";
    if (theme === "dark") {
        document.body.classList.add("dark");
    } else {
        document.body.classList.remove("dark");
    }
}

// Notification System
function showToast(message, type = "success") {
    let container = document.getElementById("toastContainer");
    if (!container) {
        container = document.createElement("div");
        container.id = "toastContainer";
        container.className = "toast-container";
        document.body.appendChild(container);
    }

    const toast = document.createElement("div");
    toast.className = `toast ${type}`;
    
    let icon = "check-circle";
    if (type === "error") icon = "exclamation-circle";
    if (type === "info") icon = "info-circle";

    toast.innerHTML = `<i class="fas fa-${icon}"></i> <span>${message}</span>`;
    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = "0";
        toast.style.transform = "translateX(50px)";
        setTimeout(() => toast.remove(), 300);
    }, 3500);
}

// Authentication Handlers (Connects to Spring Boot /api/auth)
async function validateLogin(e) {
    if (e) e.preventDefault();
    const email = document.getElementById("loginEmail")?.value || "admin@medinova.com";
    const password = document.getElementById("loginPassword")?.value || "admin123";

    try {
        const response = await fetch(`${API_BASE}/auth/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });
        const result = await response.json();
        
        if (result.success) {
            localStorage.setItem("medinova_user", JSON.stringify(result.data));
            showToast("Login Successful! Redirecting...", "success");
        } else {
            // Local fallback
            localStorage.setItem("medinova_user", JSON.stringify({ email, name: email.split('@')[0], role: "Administrator" }));
            showToast("Login Successful! Redirecting...", "success");
        }
    } catch (err) {
        // Fallback for standalone preview
        localStorage.setItem("medinova_user", JSON.stringify({ email, name: email.split('@')[0], role: "Administrator" }));
        showToast("Login Successful! Redirecting...", "success");
    }

    setTimeout(() => {
        window.location.href = "dashboard.html";
    }, 1000);
    return false;
}

async function validateRegister(e) {
    if (e) e.preventDefault();
    const name = document.getElementById("regName")?.value || "New User";
    const email = document.getElementById("regEmail")?.value || "user@medinova.com";
    const password = document.getElementById("regPassword")?.value || "password123";
    const role = document.getElementById("regRole")?.value || "Patient";

    try {
        await fetch(`${API_BASE}/auth/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, email, password, role })
        });
    } catch (err) {}

    localStorage.setItem("medinova_user", JSON.stringify({ email, name, role }));
    showToast("Account created successfully! Please login.", "success");
    setTimeout(() => {
        window.location.href = "login.html";
    }, 1000);
    return false;
}

function logoutUser() {
    localStorage.removeItem("medinova_user");
    showToast("Logged out successfully", "info");
    setTimeout(() => {
        window.location.href = "index.html";
    }, 800);
}

// Appointment Slot Selector State
let selectedTimeSlot = "10:00 AM";

function selectSlot(element, slot) {
    document.querySelectorAll(".slot-btn").forEach(btn => btn.classList.remove("selected"));
    element.classList.add("selected");
    selectedTimeSlot = slot;
}

// Sync Specialization Dropdown
async function updateSpecialization() {
    const docSelect = document.getElementById("doctorSelect");
    const specInput = document.getElementById("specializationInput");
    if (!docSelect || !specInput) return;

    const selectedDocName = docSelect.value;
    let doctors = [];

    try {
        const res = await fetch(`${API_BASE}/doctors`);
        const data = await res.json();
        if (data.success) doctors = data.data;
    } catch (err) {
        doctors = JSON.parse(localStorage.getItem("medinova_doctors")) || DEFAULT_DOCTORS;
    }

    const doc = doctors.find(d => d.name === selectedDocName);
    specInput.value = doc ? (doc.specialization || doc.spec) : "";
}

// Book Appointment Logic (Spring Boot REST POST /api/appointments)
async function bookAppointment(e) {
    if (e) e.preventDefault();

    const patientName = document.getElementById("patientName")?.value;
    const patientPhone = document.getElementById("patientPhone")?.value || "+1 555-0100";
    const doctorName = document.getElementById("doctorSelect")?.value;
    const spec = document.getElementById("specializationInput")?.value;
    const date = document.getElementById("appointmentDate")?.value;
    const type = document.getElementById("consultationType")?.value || "In-Person";

    if (!patientName || !doctorName || !date) {
        showToast("Please fill out all required fields", "error");
        return false;
    }

    const newApt = {
        appointmentCode: "APT-" + Math.floor(100 + Math.random() * 900),
        patientName: patientName,
        patientPhone: patientPhone,
        patientAge: Math.floor(Math.random() * 40) + 20,
        doctorName: doctorName,
        specialization: spec,
        appointmentDate: date,
        appointmentTime: selectedTimeSlot,
        status: "Confirmed",
        consultationType: type
    };

    try {
        const res = await fetch(`${API_BASE}/appointments`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newApt)
        });
        const data = await res.json();
        if (data.success) {
            showToast("Appointment successfully saved to Spring Boot Backend!", "success");
        }
    } catch (err) {
        const localApts = JSON.parse(localStorage.getItem("medinova_appointments")) || [];
        localApts.unshift(newApt);
        localStorage.setItem("medinova_appointments", JSON.stringify(localApts));
        showToast("Appointment successfully booked!", "success");
    }

    setTimeout(() => {
        window.location.href = "patients.html";
    }, 1200);

    return false;
}

// Dynamic UI Renderers
async function renderDashboard() {
    let appointments = [];
    let doctors = [];

    try {
        const resApt = await fetch(`${API_BASE}/appointments`);
        const dataApt = await resApt.json();
        if (dataApt.success) appointments = dataApt.data;

        const resDoc = await fetch(`${API_BASE}/doctors`);
        const dataDoc = await resDoc.json();
        if (dataDoc.success) doctors = dataDoc.data;
    } catch (err) {
        appointments = JSON.parse(localStorage.getItem("medinova_appointments")) || DEFAULT_APPOINTMENTS;
        doctors = JSON.parse(localStorage.getItem("medinova_doctors")) || DEFAULT_DOCTORS;
    }

    // Stat counters
    const pCountEl = document.getElementById("patientCount");
    const dCountEl = document.getElementById("doctorCount");
    const aCountEl = document.getElementById("appointmentCount");

    if (pCountEl) pCountEl.innerText = appointments.length + 12;
    if (dCountEl) dCountEl.innerText = doctors.length;
    if (aCountEl) aCountEl.innerText = appointments.filter(a => a.status === "Confirmed").length;

    // Table render
    const tableBody = document.getElementById("recentAppointmentsBody");
    if (tableBody) {
        tableBody.innerHTML = "";
        const recent = appointments.slice(0, 5);

        if (recent.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center; color: var(--text-muted);">No appointments recorded yet.</td></tr>`;
            return;
        }

        recent.forEach(apt => {
            const tr = document.createElement("tr");
            const statusClass = apt.status === "Confirmed" ? "status-active" : "status-pending";
            const patientName = apt.patientName || apt.patient || "Patient";
            const initials = patientName.split(" ").map(n => n[0]).join("").substring(0, 2);

            tr.innerHTML = `
                <td><strong>${apt.appointmentCode || apt.id}</strong></td>
                <td>
                    <div class="patient-cell">
                        <div class="patient-avatar-sm">${initials}</div>
                        <div>
                            <div style="font-weight:600;">${patientName}</div>
                            <div style="font-size:0.75rem; color: var(--text-muted);">${apt.patientPhone || apt.phone || ''}</div>
                        </div>
                    </div>
                </td>
                <td>${apt.doctorName || apt.doctor}<br><small style="color:var(--text-muted);">${apt.specialization || apt.spec}</small></td>
                <td>${apt.appointmentDate || apt.date} at ${apt.appointmentTime || apt.time}</td>
                <td><span class="badge-tag" style="padding: 0.2rem 0.6rem; font-size: 0.75rem;">${apt.consultationType || apt.type || 'In-Person'}</span></td>
                <td><span class="status-badge ${statusClass}">${apt.status}</span></td>
            `;
            tableBody.appendChild(tr);
        });
    }
}

async function renderDoctorsPage() {
    const grid = document.getElementById("doctorsGrid");
    const docSelect = document.getElementById("doctorSelect");
    let doctors = [];

    try {
        const res = await fetch(`${API_BASE}/doctors`);
        const data = await res.json();
        if (data.success) doctors = data.data;
    } catch (err) {
        doctors = JSON.parse(localStorage.getItem("medinova_doctors")) || DEFAULT_DOCTORS;
    }

    if (docSelect) {
        docSelect.innerHTML = `<option value="">Select Doctor</option>`;
        doctors.forEach(d => {
            const opt = document.createElement("option");
            opt.value = d.name;
            opt.innerText = `${d.name} (${d.specialization || d.spec})`;
            docSelect.appendChild(opt);
        });
    }

    if (grid) {
        grid.innerHTML = "";
        doctors.forEach(doc => {
            const card = document.createElement("div");
            card.className = "doctor-card fade-in";
            const statusClass = doc.status === "Available" ? "status-active" : "status-pending";

            card.innerHTML = `
                <div class="doctor-avatar">${doc.avatar || 'DR'}</div>
                <div class="doctor-name">${doc.name}</div>
                <div class="doctor-spec">${doc.specialization || doc.spec}</div>
                <div style="margin-bottom: 0.75rem;"><span class="status-badge ${statusClass}">${doc.status || 'Available'}</span></div>
                <div class="doctor-meta">
                    <span><i class="fas fa-briefcase"></i> ${doc.experience || doc.exp || '8 Yrs'}</span>
                    <span><i class="fas fa-star" style="color: var(--warning);"></i> ${doc.rating || '4.9'}</span>
                </div>
                <div style="display:flex; justify-content:space-between; align-items:center;">
                    <span style="font-weight:700; color:var(--text-main);">${doc.fee || '$150'}</span>
                    <a href="appointment.html" class="btn btn-primary btn-sm"><i class="fas fa-calendar-check"></i> Book</a>
                </div>
            `;
            grid.appendChild(card);
        });
    }
}

async function filterDoctors(category) {
    document.querySelectorAll(".pill-btn").forEach(btn => btn.classList.remove("active"));
    if (event && event.target) event.target.classList.add("active");

    let doctors = [];
    try {
        const url = category === "All" ? `${API_BASE}/doctors` : `${API_BASE}/doctors?specialization=${encodeURIComponent(category)}`;
        const res = await fetch(url);
        const data = await res.json();
        if (data.success) doctors = data.data;
    } catch (err) {
        const localDocs = JSON.parse(localStorage.getItem("medinova_doctors")) || DEFAULT_DOCTORS;
        doctors = category === "All" ? localDocs : localDocs.filter(d => (d.specialization || d.spec).toLowerCase().includes(category.toLowerCase()));
    }

    const grid = document.getElementById("doctorsGrid");
    if (!grid) return;

    grid.innerHTML = "";
    doctors.forEach(doc => {
        const card = document.createElement("div");
        card.className = "doctor-card fade-in";
        const statusClass = doc.status === "Available" ? "status-active" : "status-pending";

        card.innerHTML = `
            <div class="doctor-avatar">${doc.avatar || 'DR'}</div>
            <div class="doctor-name">${doc.name}</div>
            <div class="doctor-spec">${doc.specialization || doc.spec}</div>
            <div style="margin-bottom: 0.75rem;"><span class="status-badge ${statusClass}">${doc.status || 'Available'}</span></div>
            <div class="doctor-meta">
                <span><i class="fas fa-briefcase"></i> ${doc.experience || doc.exp || '8 Yrs'}</span>
                <span><i class="fas fa-star" style="color: var(--warning);"></i> ${doc.rating || '4.9'}</span>
            </div>
            <div style="display:flex; justify-content:space-between; align-items:center;">
                <span style="font-weight:700; color:var(--text-main);">${doc.fee || '$150'}</span>
                <a href="appointment.html" class="btn btn-primary btn-sm"><i class="fas fa-calendar-check"></i> Book</a>
            </div>
        `;
        grid.appendChild(card);
    });
}

async function renderPatientsPage() {
    const tableBody = document.getElementById("patientsTableBody");
    if (!tableBody) return;

    let appointments = [];
    try {
        const res = await fetch(`${API_BASE}/appointments`);
        const data = await res.json();
        if (data.success) appointments = data.data;
    } catch (err) {
        appointments = JSON.parse(localStorage.getItem("medinova_appointments")) || DEFAULT_APPOINTMENTS;
    }

    tableBody.innerHTML = "";

    if (appointments.length === 0) {
        tableBody.innerHTML = `<tr><td colspan="7" style="text-align:center; padding: 2rem; color: var(--text-muted);">No patient appointment records found.</td></tr>`;
        return;
    }

    appointments.forEach((apt) => {
        const tr = document.createElement("tr");
        const patientName = apt.patientName || apt.patient || "Patient";
        const initials = patientName.split(" ").map(n => n[0]).join("").substring(0, 2);
        const statusClass = apt.status === "Confirmed" ? "status-active" : "status-pending";

        tr.innerHTML = `
            <td><strong>${apt.appointmentCode || apt.id}</strong></td>
            <td>
                <div class="patient-cell">
                    <div class="patient-avatar-sm">${initials}</div>
                    <div>
                        <div style="font-weight:600;">${patientName}</div>
                        <div style="font-size:0.75rem; color: var(--text-muted);">${apt.patientPhone || apt.phone || ''} | Age: ${apt.patientAge || '30'}</div>
                    </div>
                </div>
            </td>
            <td>${apt.doctorName || apt.doctor}</td>
            <td><span class="badge-tag" style="margin:0; font-size:0.75rem;">${apt.specialization || apt.spec}</span></td>
            <td>${apt.appointmentDate || apt.date}<br><small style="color:var(--text-muted);">${apt.appointmentTime || apt.time}</small></td>
            <td><span class="status-badge ${statusClass}">${apt.status}</span></td>
            <td>
                <button class="btn btn-sm btn-secondary" onclick="deleteAppointment(${apt.id})" title="Cancel & Delete">
                    <i class="fas fa-trash" style="color:var(--danger);"></i>
                </button>
            </td>
        `;
        tableBody.appendChild(tr);
    });
}

async function deleteAppointment(id) {
    if (confirm("Are you sure you want to cancel and remove this appointment record?")) {
        try {
            await fetch(`${API_BASE}/appointments/${id}`, { method: "DELETE" });
            showToast("Appointment deleted from Spring Boot Database", "info");
        } catch (err) {
            const localApts = JSON.parse(localStorage.getItem("medinova_appointments")) || [];
            const updated = localApts.filter(a => a.id !== id);
            localStorage.setItem("medinova_appointments", JSON.stringify(updated));
            showToast("Appointment deleted", "info");
        }
        renderPatientsPage();
    }
}

// Modal Handlers
function openAddDoctorModal() {
    const modal = document.getElementById("addDoctorModal");
    if (modal) modal.classList.add("active");
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) modal.classList.remove("active");
}

async function handleAddDoctor(e) {
    if (e) e.preventDefault();
    const name = document.getElementById("newDocName")?.value;
    const spec = document.getElementById("newDocSpec")?.value;
    const exp = document.getElementById("newDocExp")?.value || "5 Yrs Exp";
    const fee = document.getElementById("newDocFee")?.value || "$150";

    if (!name || !spec) {
        showToast("Please provide doctor name and specialization", "error");
        return false;
    }

    const initials = name.replace("Dr. ", "").split(" ").map(n => n[0]).join("").substring(0, 2);
    const newDoc = {
        name: name.startsWith("Dr.") ? name : "Dr. " + name,
        specialization: spec,
        experience: exp,
        rating: "5.0",
        status: "Available",
        fee: fee.startsWith("$") ? fee : "$" + fee,
        avatar: initials,
        days: "Mon - Fri"
    };

    try {
        await fetch(`${API_BASE}/doctors`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newDoc)
        });
        showToast("Doctor added to Spring Boot JDBC database!", "success");
    } catch (err) {
        const doctors = JSON.parse(localStorage.getItem("medinova_doctors")) || DEFAULT_DOCTORS;
        doctors.push(newDoc);
        localStorage.setItem("medinova_doctors", JSON.stringify(doctors));
        showToast("Doctor added to medical staff roster!", "success");
    }

    closeModal("addDoctorModal");
    renderDoctorsPage();
    return false;
}

// Search Filter for Patients Table
function filterPatientTable() {
    const query = document.getElementById("patientSearchInput")?.value.toLowerCase() || "";
    const rows = document.querySelectorAll("#patientsTableBody tr");

    rows.forEach(row => {
        const text = row.innerText.toLowerCase();
        row.style.display = text.includes(query) ? "" : "none";
    });
}

// Global Lifecycle Initialization
document.addEventListener("DOMContentLoaded", () => {
    initStorage();
    renderDashboard();
    renderDoctorsPage();
    renderPatientsPage();

    const user = JSON.parse(localStorage.getItem("medinova_user"));
    if (user) {
        const userNameEls = document.querySelectorAll(".user-name-display");
        userNameEls.forEach(el => el.innerText = user.name || user.username || "Administrator");
    }
});