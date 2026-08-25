import pandas as pd
import plotly.express as px




def create_attack_heatmap(data):

    df = pd.DataFrame(data)

    day_names = {
        1: "Sunday",
        2: "Monday",
        3: "Tuesday",
        4: "Wednesday",
        5: "Thursday",
        6: "Friday",
        7: "Saturday"
    }

    df["day"] = df["day"].map(day_names)

    pivot = df.pivot_table(
        index="day",
        columns="hour",
        values="count",
        fill_value=0
    )

    day_order = [
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    ]

    pivot = pivot.reindex(day_order)

    fig = px.imshow(
        pivot,
        labels={
            "x": "Hour of Day",
            "y": "Day",
            "color": "Incidents"
        },
        title="Security Incident Activity Heatmap",
        aspect="auto"
    )

    return fig

def create_severity_chart(data):
    df = pd.DataFrame(data)

    fig = px.pie(
        df,
        names="label",
        values="count",
        hole=0.45,
        title="Incident Severity Distribution"
    )

    return fig


def create_incident_type_chart(data):
    df = pd.DataFrame(data)

    fig = px.bar(
        df,
        x="label",
        y="count",
        title="Incidents by Attack Type",
        labels={
            "label": "Attack Type",
            "count": "Number of Incidents"
        }
    )

    return fig


def create_response_status_chart(data):
    df = pd.DataFrame(data)

    fig = px.bar(
        df,
        x="label",
        y="count",
        title="Incident Response Status",
        labels={
            "label": "Response Status",
            "count": "Number of Incidents"
        }
    )

    return fig


def create_timeline_chart(data):
    df = pd.DataFrame(data)

    df["label"] = pd.to_datetime(df["label"])

    fig = px.line(
        df,
        x="label",
        y="count",
        markers=True,
        title="Security Incidents Over Time",
        labels={
            "label": "Date",
            "count": "Incidents"
        }
    )

    return fig

def create_risk_score_chart(data):
    df = pd.DataFrame(data)

    df["label"] = pd.to_numeric(df["label"])

    fig = px.bar(
        df,
        x="label",
        y="count",
        title="AI Risk Score Distribution",
        labels={
            "label": "Risk Score",
            "count": "Number of Incidents"
        }
    )

    return fig


def create_user_chart(data):
    df = pd.DataFrame(data)

    fig = px.bar(
        df,
        x="label",
        y="count",
        title="Incidents by User",
        labels={
            "label": "User",
            "count": "Incidents"
        }
    )

    return fig