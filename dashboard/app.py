import streamlit as st
import pandas as pd

from api import (
    get_dashboard_summary,
    get_severity_data,
    get_incident_type_data,
    get_response_status_data,
    get_timeline_data,
    get_user_data
)

from charts import (
    create_severity_chart,
    create_incident_type_chart,
    create_response_status_chart,
    create_timeline_chart,
    create_user_chart
)
from api import (
    get_dashboard_summary,
    get_severity_data,
    get_incident_type_data,
    get_response_status_data,
    get_timeline_data,
    get_user_data,
    get_risk_score_data
)
from charts import (
    create_severity_chart,
    create_incident_type_chart,
    create_response_status_chart,
    create_timeline_chart,
    create_user_chart,
    create_risk_score_chart
)
from api import (
    get_dashboard_summary,
    get_severity_data,
    get_incident_type_data,
    get_response_status_data,
    get_timeline_data,
    get_user_data,
    get_risk_score_data,
    get_attack_heatmap_data
)
from charts import (
    create_severity_chart,
    create_incident_type_chart,
    create_response_status_chart,
    create_timeline_chart,
    create_user_chart,
    create_risk_score_chart,
    create_attack_heatmap
)
from api import (
    get_dashboard_summary,
    get_severity_data,
    get_incident_type_data,
    get_response_status_data,
    get_timeline_data,
    get_user_data,
    get_risk_score_data,
    get_attack_heatmap_data,
    get_incidents
)
st.divider()

st.subheader("🔥 Security Activity Heatmap")

heatmap_data = get_attack_heatmap_data()

heatmap = create_attack_heatmap(heatmap_data)

st.plotly_chart(
    heatmap,
    use_container_width=True
)

risk_data = get_risk_score_data()

st.subheader("🎯 AI Risk Score Distribution")

risk_chart = create_risk_score_chart(risk_data)

st.plotly_chart(
    risk_chart,
    use_container_width=True
)


st.set_page_config(
    page_title="Security Operations Dashboard",
    page_icon="🛡️",
    layout="wide"
)


st.title("🛡️ Security Operations Dashboard")

st.caption(
    "AI-powered security incident monitoring and response system"
)


# --------------------------------------------------
# KPI SECTION
# --------------------------------------------------

try:

    summary = get_dashboard_summary()

    col1, col2, col3, col4 = st.columns(4)

    col1.metric(
        "Total Incidents",
        summary["totalIncidents"]
    )

    col2.metric(
        "Critical Incidents",
        summary["criticalIncidents"]
    )

    col3.metric(
        "High Risk Incidents",
        summary["highRiskIncidents"]
    )

    col4.metric(
        "Blocked Users",
        summary["blockedUsers"]
    )

    st.divider()


    # --------------------------------------------------
    # TIMELINE
    # --------------------------------------------------

    timeline_data = get_timeline_data()

    st.subheader("📈 Security Incidents Over Time")

    timeline_chart = create_timeline_chart(
        timeline_data
    )

    st.plotly_chart(
        timeline_chart,
        use_container_width=True
    )


    # --------------------------------------------------
    # SEVERITY + ATTACK TYPES
    # --------------------------------------------------

    col1, col2 = st.columns(2)

    with col1:

        severity_data = get_severity_data()

        severity_chart = create_severity_chart(
            severity_data
        )

        st.plotly_chart(
            severity_chart,
            use_container_width=True
        )


    with col2:

        incident_type_data = get_incident_type_data()

        incident_type_chart = create_incident_type_chart(
            incident_type_data
        )

        st.plotly_chart(
            incident_type_chart,
            use_container_width=True
        )


    # --------------------------------------------------
    # RESPONSE STATUS + USERS
    # --------------------------------------------------

    col1, col2 = st.columns(2)

    with col1:

        response_data = get_response_status_data()

        response_chart = create_response_status_chart(
            response_data
        )

        st.plotly_chart(
            response_chart,
            use_container_width=True
        )


    with col2:

        user_data = get_user_data()

        user_chart = create_user_chart(
            user_data
        )

        st.plotly_chart(
            user_chart,
            use_container_width=True
        )


    # --------------------------------------------------
    # AVERAGE RISK
    # --------------------------------------------------

    st.divider()

    st.subheader("🎯 Overall AI Risk")

    st.metric(
        "Average Risk Score",
        round(summary["averageRiskScore"], 2)
    )


except Exception as e:

    st.error(
        f"Unable to load dashboard data: {e}"
    )

st.divider()

st.subheader("🔎 Security Incident Investigation")

incidents = get_incidents()

if incidents:

    incident_df = pd.DataFrame(incidents)

    col1, col2, col3 = st.columns(3)

    severity_options = [
        "All"
    ] + sorted(
        incident_df["severity"].dropna().unique().tolist()
    )

    incident_type_options = [
        "All"
    ] + sorted(
        incident_df["incidentType"].dropna().unique().tolist()
    )

    status_options = [
        "All"
    ] + sorted(
        incident_df["status"].dropna().unique().tolist()
    )

    with col1:
        selected_severity = st.selectbox(
            "Severity",
            severity_options
        )

    with col2:
        selected_type = st.selectbox(
            "Incident Type",
            incident_type_options
        )

    with col3:
        selected_status = st.selectbox(
            "Status",
            status_options
        )

    filtered_df = incident_df.copy()

    if selected_severity != "All":
        filtered_df = filtered_df[
            filtered_df["severity"] == selected_severity
        ]

    if selected_type != "All":
        filtered_df = filtered_df[
            filtered_df["incidentType"] == selected_type
        ]

    if selected_status != "All":
        filtered_df = filtered_df[
            filtered_df["status"] == selected_status
        ]

    st.dataframe(
        filtered_df,
        use_container_width=True,
        hide_index=True
    )

else:

    st.info("No security incidents found.")