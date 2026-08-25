import requests

BASE_URL = "http://localhost:8081/api"


def get_dashboard_summary():
    response = requests.get(f"{BASE_URL}/dashboard/summary")
    response.raise_for_status()
    return response.json()


def get_severity_data():
    response = requests.get(f"{BASE_URL}/dashboard/severity")
    response.raise_for_status()
    return response.json()

def get_risk_score_data():
    response = requests.get(
        f"{BASE_URL}/dashboard/risk-distribution"
    )
    response.raise_for_status()
    return response.json()


def get_incident_type_data():
    response = requests.get(f"{BASE_URL}/dashboard/incident-types")
    response.raise_for_status()
    return response.json()

def get_attack_heatmap_data():
    response = requests.get(
        f"{BASE_URL}/dashboard/attack-heatmap"
    )
    response.raise_for_status()
    return response.json()


def get_response_status_data():
    response = requests.get(f"{BASE_URL}/dashboard/response-status")
    response.raise_for_status()
    return response.json()


def get_timeline_data():
    response = requests.get(f"{BASE_URL}/dashboard/timeline")
    response.raise_for_status()
    return response.json()


def get_user_data():
    response = requests.get(f"{BASE_URL}/dashboard/users")
    response.raise_for_status()
    return response.json()

def get_incidents():
    response = requests.get(
        f"{BASE_URL}/incidents"
    )
    response.raise_for_status()
    return response.json()