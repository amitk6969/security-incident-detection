import streamlit as st
import pandas as pd
from streamlit_autorefresh import st_autorefresh

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

from charts import (
    create_severity_chart,
    create_incident_type_chart,
    create_response_status_chart,
    create_timeline_chart,
    create_user_chart,
    create_risk_score_chart,
    create_attack_heatmap
)


# ==================================================
# PAGE CONFIGURATION
# ==================================================

st.set_page_config(
    page_title="Security Operations Dashboard",
    page_icon="🛡️",
    layout="wide"
)

st_autorefresh(
    interval=30000,
    key="security_dashboard_refresh"
)# ==================================================
# HEADER
# ==================================================

st.title("🛡️ Security Operations Dashboard")

st.caption(
    "AI-powered security incident monitoring and response system"
)


# ==================================================
# LOAD DATA
# ==================================================

try:

    summary = get_dashboard_summary()

    timeline_data = get_timeline_data()
    severity_data = get_severity_data()
    incident_type_data = get_incident_type_data()
    response_data = get_response_status_data()
    user_data = get_user_data()
    risk_data = get_risk_score_data()
    heatmap_data = get_attack_heatmap_data()
    incidents = get_incidents()


    # ==================================================
    # KPI CARDS
    # ==================================================

    col1, col2, col3, col4 = st.columns(4)

    with col1:
        st.metric(
            "Total Incidents",
            summary.get("totalIncidents", 0)
        )

    with col2:
        st.metric(
            "Critical Incidents",
            summary.get("criticalIncidents", 0)
        )

    with col3:
        st.metric(
            "High Risk Incidents",
            summary.get("highRiskIncidents", 0)
        )

    with col4:
        st.metric(
            "Blocked Users",
            summary.get("blockedUsers", 0)
        )


    # ==================================================
    # CRITICAL SECURITY ALERTS
    # ==================================================

    st.divider()

    st.subheader("🚨 Critical Security Alerts")

    critical_incidents = []

    for incident in incidents:

        severity = str(
            incident.get("severity", "")
        ).upper()

        try:
            risk_score = float(
                incident.get("riskScore", 0)
            )
        except (ValueError, TypeError):
            risk_score = 0

        if severity == "CRITICAL" or risk_score >= 90:

            critical_incidents.append(
                incident
            )


    if critical_incidents:

        for incident in critical_incidents[:5]:

            with st.container(border=True):

                col1, col2, col3, col4 = st.columns(4)

                with col1:

                    st.write(
                        f"**Incident:** "
                        f"{incident.get('id', 'N/A')}"
                    )

                with col2:

                    st.write(
                        f"**Severity:** "
                        f"{incident.get('severity', 'N/A')}"
                    )

                with col3:

                    st.write(
                        f"**Risk Score:** "
                        f"{incident.get('riskScore', 'N/A')}"
                    )

                with col4:

                    st.write(
                        f"**Status:** "
                        f"{incident.get('status', 'N/A')}"
                    )

    else:

        st.success(
            "No critical security incidents detected."
        )


    # ==================================================
    # AVERAGE RISK
    # ==================================================

    st.divider()

    st.subheader("🎯 Overall AI Risk")

    st.metric(
        "Average Risk Score",
        round(
            summary.get("averageRiskScore", 0),
            2
        )
    )


    # ==================================================
    # INCIDENT TIMELINE
    # ==================================================

    st.divider()

    timeline_chart = create_timeline_chart(
        timeline_data
    )

    st.plotly_chart(
        timeline_chart,
        use_container_width=True,
        key="timeline_chart"
    )


    # ==================================================
    # SEVERITY + ATTACK TYPE
    # ==================================================

    col1, col2 = st.columns(2)


    with col1:

        severity_chart = create_severity_chart(
            severity_data
        )

        st.plotly_chart(
            severity_chart,
            use_container_width=True,
            key="severity_chart"
        )


    with col2:

        incident_type_chart = create_incident_type_chart(
            incident_type_data
        )

        st.plotly_chart(
            incident_type_chart,
            use_container_width=True,
            key="attack_type_chart"
        )


    # ==================================================
    # RESPONSE STATUS + USER ACTIVITY
    # ==================================================

    col1, col2 = st.columns(2)


    with col1:

        response_chart = create_response_status_chart(
            response_data
        )

        st.plotly_chart(
            response_chart,
            use_container_width=True,
            key="response_status_chart"
        )


    with col2:

        user_chart = create_user_chart(
            user_data
        )

        st.plotly_chart(
            user_chart,
            use_container_width=True,
            key="user_chart"
        )


    # ==================================================
    # RISK SCORE DISTRIBUTION
    # ==================================================

    st.divider()

    risk_chart = create_risk_score_chart(
        risk_data
    )

    st.plotly_chart(
        risk_chart,
        use_container_width=True,
        key="risk_score_chart"
    )


    # ==================================================
    # SECURITY ACTIVITY HEATMAP
    # ==================================================

    st.divider()

    heatmap = create_attack_heatmap(
        heatmap_data
    )

    st.plotly_chart(
        heatmap,
        use_container_width=True,
        key="attack_heatmap"
    )


    # ==================================================
    # INCIDENT INVESTIGATION
    # ==================================================

    st.divider()

    st.subheader(
        "🔎 Security Incident Investigation"
    )


    if incidents:

        incident_df = pd.DataFrame(
            incidents
        )


        # ----------------------------------------------
        # FILTERS
        # ----------------------------------------------

        col1, col2, col3 = st.columns(3)


        severity_options = [
            "All"
        ] + sorted(
            incident_df["severity"]
            .dropna()
            .unique()
            .tolist()
        )


        incident_type_options = [
            "All"
        ] + sorted(
            incident_df["incidentType"]
            .dropna()
            .unique()
            .tolist()
        )


        status_options = [
            "All"
        ] + sorted(
            incident_df["status"]
            .dropna()
            .unique()
            .tolist()
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


        # ----------------------------------------------
        # APPLY FILTERS
        # ----------------------------------------------

        filtered_df = incident_df.copy()


        if selected_severity != "All":

            filtered_df = filtered_df[
                filtered_df["severity"]
                == selected_severity
            ]


        if selected_type != "All":

            filtered_df = filtered_df[
                filtered_df["incidentType"]
                == selected_type
            ]


        if selected_status != "All":

            filtered_df = filtered_df[
                filtered_df["status"]
                == selected_status
            ]


        # ----------------------------------------------
        # INCIDENT DETAILS
        # ----------------------------------------------

        st.subheader("📋 Incident Details")

        if not filtered_df.empty:

            incident_ids = filtered_df[
                "id"
            ].tolist()

            selected_incident_id = st.selectbox(
                "Select an incident",
                incident_ids
            )

            selected_incident = filtered_df[
                filtered_df["id"]
                == selected_incident_id
            ].iloc[0]


            st.divider()

            col1, col2, col3, col4 = st.columns(4)


            with col1:

                st.metric(
                    "Risk Score",
                    selected_incident.get(
                        "riskScore",
                        "N/A"
                    )
                )


            with col2:

                st.metric(
                    "Severity",
                    selected_incident.get(
                        "severity",
                        "N/A"
                    )
                )


            with col3:

                st.metric(
                    "Confidence",
                    selected_incident.get(
                        "confidence",
                        "N/A"
                    )
                )


            with col4:

                st.metric(
                    "Status",
                    selected_incident.get(
                        "status",
                        "N/A"
                    )
                )


            st.subheader(
                "🤖 AI Security Analysis"
            )

            st.write(
                selected_incident.get(
                    "explanation",
                    "No explanation available."
                )
            )


            st.subheader(
                "🛡️ Recommended Action"
            )

            st.write(
                selected_incident.get(
                    "recommendedAction",
                    "No recommended action available."
                )
            )


        else:

            st.info(
                "No incidents match the selected filters."
            )


        # ----------------------------------------------
        # INCIDENT TABLE
        # ----------------------------------------------

        st.dataframe(
            filtered_df,
            use_container_width=True,
            hide_index=True
        )


    else:

        st.info(
            "No security incidents found."
        )


# ==================================================
# ERROR HANDLING
# ==================================================

except Exception as e:

    st.error(
        f"Unable to load dashboard data: {e}"
    )